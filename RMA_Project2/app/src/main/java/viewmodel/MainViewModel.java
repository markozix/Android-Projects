package viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import repository.RasporedRepository;
import repository.db.entity.RasporedEntity;
import repository.web.model.Resource;

public class MainViewModel extends AndroidViewModel {

    private RasporedRepository rasporedRepository;

    public MainViewModel(@NonNull Application application){
        super(application);
        rasporedRepository = new RasporedRepository(application);

    }


    public LiveData<Resource<Void>> getRasporedi() {
        return rasporedRepository.getRasporedi();
    }

    public LiveData<List<RasporedEntity>> getAllRasporedi(){
        return rasporedRepository.getAllRasporedi();
    }

}
