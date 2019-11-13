package fragmenti;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projekat1.R;

import java.util.ArrayList;
import java.util.List;

import adapteri.CategoryAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import viewModel.MainViewModel;

public class FourthFragment extends Fragment {

    MainViewModel mainViewModel;

    EditText editText;

    CategoryAdapter madapter;

    List<String> mlistaKategorija = new ArrayList<>();

    public static FourthFragment newInstance() {
        return new FourthFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fourth_fragment, container, false);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        editText = view.findViewById(R.id.editTextCategory);

        Button btnAdd = view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String str = editText.getText().toString();
               mainViewModel.addCategory(str);
            }
        });

        RecyclerView recycler = view.findViewById(R.id.recyclerViewFF);

        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());

        recycler.setLayoutManager(manager);

        madapter = new CategoryAdapter();
        recycler.setAdapter(madapter);



    return view;


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getmCategoryLiveData().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> kategorije) {
                mlistaKategorija = new ArrayList<>(kategorije);
                madapter.setData(mlistaKategorija);
            }
        });



    }
}
