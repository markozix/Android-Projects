package com.example.homework4;

import adapter.LokacijaAdapter;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import model.Lokacija;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoreInfoActivity extends AppCompatActivity {


    private GoogleMap gMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    public static final int REQUEST_LOCATION_PERMISSION = 100;
    private int state = 1;
    private DatabaseReference reference;
    private MainActivity mainActivity;
    private LatLng latLang;
    private Button btnRemove;
    private LokacijaAdapter adapter;
    private List<Lokacija> lista = new ArrayList<>();
    private String id;
    private EditText et_title;
    private EditText et_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        init();
    }

    private void init(){
        initLocationClient();
        initFir();
        initUI();
        getInten();
    }
    private void initFir(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


        reference = firebaseDatabase.getReference().child("Location");
    }

    private void initLocationClient(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private void getInten(){
        Intent i = getIntent();
        id = i.getStringExtra("id");
        double longitude = i.getDoubleExtra("longitude",0);
        double latitude = i.getDoubleExtra("latitude",0);
        String name = i.getStringExtra("name");
        String date = i.getStringExtra("date");
        String description = i.getStringExtra("description");

        et_title = findViewById(R.id.et_title);
        TextView tv_date = findViewById(R.id.et_date);
        et_description = findViewById(R.id.et_description);

        et_title.setText(name);
        et_title.setEnabled(false);

        tv_date.setText(date);

        et_description.setText(description);
        et_description.setEnabled(false);


//        String id = mainActivity.getLokacije().get(position).getId();
//        double latitude = mainActivity.getLokacije().get(position).getLatitude();
//        double longitude = mainActivity.getLokacije().get(position).getLongitude();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    if(child.getKey().equals(id)){
                        latLang = new LatLng(latitude,longitude);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void initUI(){

        btnRemove = findViewById(R.id.btnRemove);
        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel2 = findViewById(R.id.btnCancel2);


        btnEdit.setVisibility(View.VISIBLE);
        btnRemove.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);

        btnCancel2.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);



        MapFragment mapFragment = MapFragment.newInstance();
        getFragmentManager().beginTransaction().add(R.id.mapaFrame,mapFragment).commit();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
               gMap = googleMap;
               gMap.getUiSettings().setZoomControlsEnabled(true);
               updateMap(latLang);
               gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLang,15));

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot child : dataSnapshot.getChildren()){
                            if(child.getKey().equals(id)){
                                reference.child(id).removeValue();
                                finish();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }


        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEdit.setVisibility(View.GONE);
                btnRemove.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);

                btnCancel2.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.VISIBLE);

                et_title.setEnabled(true);
                et_description.setEnabled(true);



            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot child : dataSnapshot.getChildren()){
                            if(child.getKey().equals(id)){
                                Lokacija lok = child.getValue(Lokacija.class);
                                lok.setDescription(et_description.getText().toString());
                                lok.setLocationName(et_title.getText().toString());


                                reference.child(id).setValue(lok);
                            }
                        }
                        finish();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEdit.setVisibility(View.VISIBLE);
                btnRemove.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);

                btnCancel2.setVisibility(View.GONE);
                btnSave.setVisibility(View.GONE);

                et_title.setEnabled(false);
                et_description.setEnabled(false);
            }
        });


    }

    private void updateMap(LatLng latLng){
        gMap.addMarker(new MarkerOptions().position(latLng).title("Here we are!"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //animacija zomiranja
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
    }

    @SuppressLint("MissingPermission")
    private void getLocation(){
        if(!hasAnyFeature(PackageManager.FEATURE_LOCATION,PackageManager.FEATURE_LOCATION_GPS,PackageManager.FEATURE_LOCATION_NETWORK)){
            Toast toast = Toast.makeText(getApplicationContext(),"No feature available",Toast.LENGTH_SHORT);
            return;
        }

        if(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)){
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if(task.isSuccessful()){
                        Location location = task.getResult();
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        LatLng latLng = new LatLng(latitude,longitude);
                        updateMap(latLng);
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Something went wrong!",Toast.LENGTH_SHORT);
                    }
                }
            });
        }else{
            requestPermission(REQUEST_LOCATION_PERMISSION, Manifest.permission.ACCESS_FINE_LOCATION);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],int[] grantResult){
        switch (requestCode){
            case REQUEST_LOCATION_PERMISSION:
                if(grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Permission granted!",Toast.LENGTH_SHORT);
                }
        }
    }

    protected boolean hasAnyFeature(String... features){
        for(String feature : features){
            if(getPackageManager().hasSystemFeature(feature)){
                return true;
            }
        }
        return false;
    }

    protected boolean hasPermission(String...permissions){
        for(String permission : permissions){
            boolean hasPermission = ContextCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED;
            if(!hasPermission){
                return false;
            }
        }
        return true;
    }

    protected void requestPermission(int requestCode, String... permissions){
        ActivityCompat.requestPermissions(this,permissions,requestCode);
    }

}
