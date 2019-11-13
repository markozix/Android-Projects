package fragmenti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projekat1.DetalisActivity;
import com.example.projekat1.MainActivity;
import com.example.projekat1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapteri.TrosakAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import model.Trosak;
import viewModel.MainViewModel;

import static android.content.ContentValues.TAG;
import static com.example.projekat1.R.id.action_menu_divider;
import static com.example.projekat1.R.id.btnDetails;

public class SecondFragment extends Fragment {

    private MainViewModel mainViewModel;
    private List<Trosak> mlistaTroskova = new ArrayList<>();
    private TrosakAdapter madapter;
    private List<String> listaKategorija = new ArrayList<>();
    private EditText editText;


    public static SecondFragment newInstance() {
        return new SecondFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_second_fragment, container, false);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Spinner dropDown = view.findViewById(R.id.dropDown);

        listaKategorija = mainViewModel.getmCategoryLiveData().getValue();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, listaKategorija);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dropDown.setAdapter(adapter);

        editText = view.findViewById(R.id.filterEditText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mainViewModel.setFilter(editText.getText().toString(),dropDown.getSelectedItem().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Button btnApply = view.findViewById(R.id.btnApply);


        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //filtrira prikaz u odnosu na kategoriju
                String kata = dropDown.getSelectedItem().toString();
                mainViewModel.setFilterCategory(kata,editText.getText().toString());
            }
        });




        RecyclerView recycler = view.findViewById(R.id.recyclerViewSF);




        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());

        recycler.setLayoutManager(manager);

        madapter = new TrosakAdapter();

        madapter.setOnItemRemoveCallback(new TrosakAdapter.OnItemRemoveCallback() {
            @Override
            public void onItemRemove(int position) {
                madapter.removeItem(position);
            }
        });

        madapter.setOnDetailsClicked(new TrosakAdapter.OnDetailsClicked() {
            @Override
            public void onDetailsClicked(Trosak trosak,int position) {
                Intent i = new Intent(getContext(), DetalisActivity.class);
                i.putExtra("name",  trosak.getName());
                i.putExtra("cost",  Integer.toString(trosak.getCost()));
                i.putExtra("category", trosak.getCategory());
                i.putExtra("date", trosak.getDate().toString());
                i.putExtra("position", position);

                startActivityForResult(i,1);
            }
        });


        recycler.setAdapter(madapter);


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {


            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(SecondFragment.this.getContext(), "Trosak br: " + data.getIntExtra("position",0) + " je obrisan", Toast.LENGTH_SHORT).show();

                madapter.removeItem(data.getIntExtra("position",0) );


            }
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getmTroskoviLiveData().observe(getViewLifecycleOwner(), new Observer<List<Trosak>>() {
            @Override
            public void onChanged(List<Trosak> trosaks) {
                mlistaTroskova = new ArrayList<>(trosaks);
                madapter.setData(mlistaTroskova);
            }
        });



        mainViewModel.getmCategoryLiveData().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                for(String s : strings){
                    if(!listaKategorija.contains(s)){
                        listaKategorija.add(s);
                    }
                }

            }
        });


    }
}















