package repository;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import repository.db.RasporedDatabase;
import repository.db.dao.RasporedDao;
import repository.db.entity.RasporedEntity;
import repository.web.api.RasporedApi;
import repository.web.model.RasporedApiModel;
import repository.web.model.Resource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RasporedRepository {


    private static final String TAG = "RasporedRepository";

    private RasporedApi rasporedApi;
    private RasporedDao rasporedDao;
    private MutableLiveData<Resource<Void>> mResourceLiveData;
    private ExecutorService executorService;

    public RasporedRepository(Application application){
        rasporedApi = new RasporedApi();
        rasporedDao = RasporedDatabase.getDb(application).getRasporedDao();
        mResourceLiveData = new MutableLiveData<>();
        executorService = Executors.newCachedThreadPool();
    }



    public LiveData<Resource<Void>> getRasporedi(){
        rasporedApi.getRasporedi().enqueue(new Callback<List<RasporedApiModel>>() {
            @Override
            public void onResponse(Call<List<RasporedApiModel>> call, Response<List<RasporedApiModel>> response) {
                notifyResult(true);
                insertRasporedi(response.body());
            }

            @Override
            public void onFailure(Call<List<RasporedApiModel>> call, Throwable t) {
                notifyResult(false);
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });

        return mResourceLiveData;
    }

    public LiveData<List<RasporedEntity>> getAllRasporedi(){
        return rasporedDao.getAllRasporedi();
    }




    private void notifyResult(boolean isSuccessful){
        // Since we are storing movies in a database, there is no need to send
        // the response we got from the server to the activity, we'll just wait
        // for the data to be inserted into db, and at that moment all observers observing
        // movie table will be notified. So we just send Void, and a flag if fetch was successful.
        Resource<Void> resource = new Resource<>(null, isSuccessful);
        mResourceLiveData.setValue(resource);
    }

    private void insertRasporedi(List<RasporedApiModel> rasporedApiModelList){
        List<RasporedEntity> rasporedEntityList = transformApiModelToEntity(rasporedApiModelList);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                rasporedDao.insertRasporedi(rasporedEntityList);
            }
        });
    }

    private List<RasporedEntity> transformApiModelToEntity(List<RasporedApiModel> rasporedApiModelList) {
        List<RasporedEntity> rasporedEntityList = new ArrayList<>();

        for(RasporedApiModel rasporedApiModel : rasporedApiModelList){
            int id = rasporedApiModel.getId();
            String predmet = rasporedApiModel.getPredmet();
            String tip = rasporedApiModel.getTip();
            String nastavnik = rasporedApiModel.getNastavnik();
            String grupe = rasporedApiModel.getGrupe();
            String dan = rasporedApiModel.getDan();
            String termin = rasporedApiModel.getTermin();
            String ucionica = rasporedApiModel.getUcionica();

            RasporedEntity rasporedEntity = new RasporedEntity(id,predmet,tip,nastavnik,grupe,dan,termin,ucionica);
            rasporedEntityList.add(rasporedEntity);
        }
        return rasporedEntityList;

    }


}
