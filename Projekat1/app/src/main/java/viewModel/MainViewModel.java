package viewModel;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import model.Trosak;

public class MainViewModel extends ViewModel {


    private MutableLiveData<HashMap<String,String>> mChartLiveData;
    private MutableLiveData<List<Trosak>> mTroskoviLiveData;
    private MutableLiveData<List<String>> mCategoryList;


    private List<Trosak> mTroskoviList;
    private List<String> mCategories;


    public MainViewModel() {
        mTroskoviLiveData = new MutableLiveData<>();
        mTroskoviList =  new ArrayList<>();
        mCategories = new ArrayList<>();
        mCategoryList = new MutableLiveData<>();

        mCategories.add("Reset");
        mCategories.add("Fun");
        mCategories.add("Work");
        mCategories.add("Food");
        mCategories.add("Home");

        mCategoryList.setValue(mCategories);

        for(int i =0;i<20;i++){
            mTroskoviList.add(new Trosak("Trosak" + i, i,"Random",new Date()));
        }
        mTroskoviLiveData.setValue(mTroskoviList);




    }


    public LiveData<List<Trosak>> getmTroskoviLiveData() {
        return mTroskoviLiveData;
    }

    public LiveData<List<String>> getmCategoryLiveData(){
        return mCategoryList;
    }

    public void addCategory(String str){

        if(!mCategories.contains(str)) {
            mCategories.add(str);
            mCategoryList.setValue(mCategories);
        }


    }

//mozda ovdje update za dodavanje u hes ako ga prebacim u ovdje


    public void addTrosak(Trosak trosak){
        mTroskoviList.add(trosak);
        mTroskoviLiveData.setValue(mTroskoviList);
    }

    public void setFilterCategory(String filter, String text){
        List<Trosak> filtriranaLista = new ArrayList<>();
        for(Trosak t : mTroskoviList){
            if(filter.equals("Reset") && text.equals("")) {
                filtriranaLista.add(t);
            }else {
                if (t.getCategory().equals(filter) && t.getName().startsWith(text)) {
                    filtriranaLista.add(t);
                }
            }
        }
        mTroskoviLiveData.setValue(filtriranaLista);
    }

    public void setFilter(String filter, String kategorija){
        filter = filter.toLowerCase();
        List<Trosak> filtriranaLista = new ArrayList<>();
        for(Trosak t : mTroskoviList){
            if(kategorija.equals("Reset") && filter.equals("")) {
                    filtriranaLista.add(t);
            }else{
                if (t.getName().toLowerCase().startsWith(filter) && t.getCategory().equals(kategorija)) {
                    filtriranaLista.add(t);
                }
            }
        }
        mTroskoviLiveData.setValue(filtriranaLista);

    }


    public void setmTroskoviLiveData(MutableLiveData<List<Trosak>> mTroskoviLiveData) {
        this.mTroskoviLiveData = mTroskoviLiveData;
    }

    public List<Trosak> getmTroskoviList() {
        return mTroskoviList;
    }

    public void setmTroskoviList(List<Trosak> mTroskoviList) {
        this.mTroskoviList = mTroskoviList;
    }


    public void setmCategoryList(MutableLiveData<List<String>> mCategoryList) {
        this.mCategoryList = mCategoryList;
    }

    public List<String> getmCategories() {
        return mCategories;
    }

    public void setmCategories(List<String> mCategories) {
        this.mCategories = mCategories;
    }
}
