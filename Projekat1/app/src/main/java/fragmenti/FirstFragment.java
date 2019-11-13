package fragmenti;


import android.R.layout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projekat1.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import model.Trosak;
import viewModel.MainViewModel;

public class FirstFragment extends Fragment {


    private MainViewModel mainViewModel;
    private EditText meditTextName;
    private EditText meditTextCost;
    private Button btnAdd;
    private Spinner dropDownMenu;
    private ArrayList<String> lista = new ArrayList<>();



    public static FirstFragment newInstance(String arg) {return new FirstFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_first_fragment, container,false);

        //NEMOJ DA ZABORAVIS DA OVO MORA DA IDE OVDJE MARKO DEBILU!!!!!!!!!!!!!
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        meditTextName = view.findViewById(R.id.expenseNameLabel);
        meditTextCost = view.findViewById(R.id.expenseCostLabel);
        btnAdd = view.findViewById(R.id.btnAdd);
        dropDownMenu = view.findViewById(R.id.dropDownMenu);
        for(int i=0;i<mainViewModel.getmCategories().size();i++){
            if(!lista.contains(mainViewModel.getmCategories().get(i))){
                lista.add(mainViewModel.getmCategories().get(i));
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), layout.simple_spinner_item,lista);
        adapter.setDropDownViewResource(layout.simple_dropdown_item_1line);
        dropDownMenu.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected = dropDownMenu.getSelectedItem().toString();
                int cost = 0;
                String name = meditTextName.getText().toString();
                if(!meditTextCost.getText().toString().isEmpty()){
                    cost = Integer.parseInt(meditTextCost.getText().toString());
                }

                if(!name.isEmpty() && cost > 0 && !selected.equals("Reset")) {
                    //moze se staviti na prvo mjesto category pa provjeriti da to nije izabrano
                    //i onda pustiti korisnika da ide dalje, u trenutnoj implementacji barem, stavljeno
                    //je da provjerava za prazan string da se ispostuje zahtjev projekta,
                    //i naznaci mjesto gdje bi se provjeravalo da je drugacija implementacija gdje ima
                    //mogucnost da dodje prazan string

                    Trosak trosak = new Trosak(name, cost, selected, new Date());
                    mainViewModel.addTrosak(trosak);
                    //test za dodavanje kategorije, radi ovako treba vidjeti sa vise prozora
                    mainViewModel.addCategory("Novakata");
                    Toast.makeText(FirstFragment.this.getContext(),"Trosak br: " +mainViewModel.getmTroskoviList().size()+ " je dodat", Toast.LENGTH_SHORT).show();

                }else if(name.isEmpty()){

                    Toast.makeText(FirstFragment.this.getContext(),"Niste unijeli ime!", Toast.LENGTH_SHORT).show();

                } else if (cost == 0) {
                    Toast.makeText(FirstFragment.this.getContext(),"Ne moze trosak biti 0!", Toast.LENGTH_SHORT).show();

                } else if(selected.equals("Reset")){
                    Toast.makeText(FirstFragment.this.getContext(),"Ne moze dummy grupa da se izabere!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(FirstFragment.this.getContext(),"Unestie podatke", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return view;

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);


        mainViewModel.getmCategoryLiveData().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            //ovo bi trebalo da nasetuje novu listu u dropDownMeni kad se doda na nekom drugom fragmentu
            public void onChanged(List<String> strings) {
                for (String s : strings) {
                    if(!lista.contains(s)){
                        lista.add(s);
                    }
                }



            }
        });





    }
}
