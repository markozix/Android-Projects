package fragmenti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projekat1.R;

import java.util.HashMap;
import java.util.List;

import customView.PercentageTextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import model.Trosak;
import viewModel.MainViewModel;

public class ThirdFragment extends Fragment {


    private MainViewModel mainViewModel;
    private List<Trosak> listaTroskova;
    private List<String> listaKategorija;
    private HashMap<String,String> hesMapa = new HashMap<>();
    private PercentageTextView customView;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_third_fragment, container, false);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        customView = view.findViewById(R.id.custom);


        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }
}
