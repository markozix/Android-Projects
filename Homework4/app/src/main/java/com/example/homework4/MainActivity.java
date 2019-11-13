package com.example.homework4;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapter.LokacijaAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import model.Lokacija;


public class MainActivity extends AppCompatActivity {

    private LokacijaAdapter adapter;
    private LinearLayoutManager manager;
    private DatabaseReference reference;
    private ValueEventListener valueEventListener;
    private ChildEventListener childEventListener;
    private List<Lokacija> lokacije = new ArrayList<>();
    private RecyclerView recycler;
    private static final int REQUEST_LOCATION_PERMISSION = 100;
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){

        initFir();
        initViews();


    }


    private void initViews(){

        Button btnPlus = findViewById(R.id.btnPlus);

        recycler = findViewById(R.id.recycler);
        manager = new LinearLayoutManager(this);

        recycler.setLayoutManager(manager);

        adapter = new LokacijaAdapter();

        adapter.setOnItemClickedCallback(new LokacijaAdapter.OnItemClickedCallback() {
            @Override
            public void onItemClicked(int position) {
               Intent intent = new Intent(getApplicationContext(),MoreInfoActivity.class);

               String id = lokacije.get(position).getId();
               double latitude = lokacije.get(position).getLatitude();
               double longitude = lokacije.get(position).getLongitude();
               String name = lokacije.get(position).getLocationName();
               String date = lokacije.get(position).getDate();
               String description = lokacije.get(position).getDescription();

               intent.putExtra("id", id);
               intent.putExtra("latitude",latitude);
               intent.putExtra("longitude",longitude);
               intent.putExtra("name",name);
               intent.putExtra("date",date);
               intent.putExtra("description",description);

               startActivity(intent);
            }
        });

        recycler.setAdapter(adapter);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager lm = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
                if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                    Intent intent = new Intent(getApplicationContext(), NewLocationActivity.class);
                    startActivity(intent);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Turn your GPS on!", Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });


    }




    private void initFir(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);


        reference = firebaseDatabase.getReference().child("Location");
        /*Lokacija testLok = new Lokacija();
        testLok.setLocationName("TestLokacija");
        testLok.setDate("12.2.2020.");
        testLok.setDescription("Testing decsription of location");

        reference.push().setValue(testLok);
*/


        createListeners();
    }

    private void createListeners(){

        valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lokacije.clear();

                if(dataSnapshot.getValue() == null){
                    return;
                }

                for(DataSnapshot child : dataSnapshot.getChildren()){
                    Lokacija lokacija = child.getValue(Lokacija.class);
                    String key = child.getKey();
                    lokacija.setId(key);

                    lokacije.add(lokacija);

                }

                adapter.setData(lokacije);

                int lastIndex = lokacije.size() - 1;
                manager.scrollToPosition(lastIndex);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        reference.addValueEventListener(valueEventListener);


        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast toast = Toast.makeText(getApplicationContext(),"Child added ", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast toast = Toast.makeText(getApplicationContext(),"Child changed ", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Toast toast = Toast.makeText(getApplicationContext(),"Child removed", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        reference.addChildEventListener(childEventListener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reference.removeEventListener(valueEventListener);
        reference.removeEventListener(childEventListener);
    }



}
