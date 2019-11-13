package com.example.domaci2_marko_vukicevic_rn2916;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import adapter.Adapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import model.Student;
import view.MainView;

public class MainActivity extends AppCompatActivity {


    private EditText txtNaVrhu;
    private EditText etZaUnos;
    private MainView mView;
    private List<Student> listaStudenata;
    private List<Student> pomocnaLista;
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){

        pomocnaLista = new ArrayList<>();
        listaStudenata = new ArrayList<>();
        initViews();
        initMView();

    }

    private void initMView(){

        mView = ViewModelProviders.of(this).get(MainView.class);
        mView .getStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                listaStudenata = new ArrayList<>(students);
                adapter.setData(listaStudenata);
            }
        });

    }

    private void initViews(){

        etZaUnos = findViewById(R.id.et_naDnu);
        Button btn = findViewById(R.id.btn_add);
        txtNaVrhu = findViewById(R.id.et_naVrhu);

        txtNaVrhu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                String s1 = txtNaVrhu.getText().toString();
                pomocnaLista.clear();
                for(int i=0;i<listaStudenata.size();i++){
                    if(s1.isEmpty()){
                        btn.setEnabled(true);
                    }else{
                        btn.setEnabled(false);
                    }
                    if(listaStudenata.get(i).getName().contains(s1)){
                        pomocnaLista.add(listaStudenata.get(i));
                    }
                }
                adapter.setData(pomocnaLista);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = etZaUnos.getText().toString();
                listaStudenata.add(2,new Student(str, "Mejl dodatog studenta"));
                mView.setStudents(listaStudenata);

                //ako se daju neki bonus poeni na ovo
                /*
                for(int i = 0; i < listaStudenata.size();i++){
                    if(listaStudenata.get(i).getName().contains(str)){
                        listaStudenata.remove(i);
                        adapter.notifyItemRemoved(i);
                    }
                }
                */

            }
        });

        RecyclerView recycler = findViewById(R.id.recyclerView);

        GridLayoutManager gridManager = new GridLayoutManager(this,2);
        recycler.setLayoutManager(gridManager);

        adapter = new Adapter();
        recycler.setAdapter(adapter);


    }

}
