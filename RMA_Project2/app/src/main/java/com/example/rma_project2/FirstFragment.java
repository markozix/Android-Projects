package com.example.rma_project2;

import adapteri.RasporedItemAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import repository.db.entity.RasporedEntity;
import repository.web.model.Resource;
import viewmodel.MainViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class FirstFragment extends Fragment {

    private MainViewModel mainViewModel;
    private RasporedItemAdapter adapter;
    public static FirstFragment newInstance(String arg) {return new FirstFragment();}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_first_fragment, container,false);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Spinner dropDownDan = view.findViewById(R.id.dropDownDan);
        Spinner dropDownGrupa = view.findViewById(R.id.dropDownGrupa);

        String dani[] = {"PON","UTO","SRE","CET","PET"};
        String grupe[] = {"101","102","103","104","201","202","203","204","301","302","303","401","402","403"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, dani);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownDan.setAdapter(adapter2);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, grupe);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownGrupa.setAdapter(adapter1);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(manager);

        adapter = new RasporedItemAdapter();

        recyclerView.setAdapter(adapter);

        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getAllRasporedi().observe(this, new Observer<List<RasporedEntity>>() {
            @Override
            public void onChanged(List<RasporedEntity> rasporedEntities) {
                adapter.setData(rasporedEntities);
            }
        });

        mainViewModel.getRasporedi().observe(this, new Observer<Resource<Void>>() {
            @Override
            public void onChanged(Resource<Void> voidResource) {
                Toast.makeText(getContext(),"Radi", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
