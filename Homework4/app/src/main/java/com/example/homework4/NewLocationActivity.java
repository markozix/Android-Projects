package com.example.homework4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.Manifest;
import android.annotation.SuppressLint;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import model.Lokacija;

public class NewLocationActivity extends AppCompatActivity {

        private GoogleMap gMap;
        private FusedLocationProviderClient fusedLocationProviderClient;
        public static final int REQUEST_LOCATION_PERMISSION = 100;
        private EditText et_title;
        private EditText et_description;
        private DatabaseReference reference;
        private double latitude;
        private double longitude;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_location);

            init();
        }

        private void init(){
            initLocationClient();
            initUI();
            initFir();
        }

        private void initFir(){

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();



            reference = firebaseDatabase.getReference().child("Location");

        }

        private void initLocationClient(){
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        }

        private void initUI(){

            Button btnStore = findViewById(R.id.btnStore);
            Button btnCanel = findViewById(R.id.btnCancel);

            et_title = findViewById(R.id.et_title);
            et_title.setEnabled(false);
            et_description = findViewById(R.id.ed_description);
            et_description.setEnabled(false);
            TextView tv_date = findViewById(R.id.tv_date);
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            tv_date.setText(formatter.format(date));


            MapFragment mapFragment = MapFragment.newInstance();
            getFragmentManager().beginTransaction().add(R.id.mapaFrame,mapFragment).commit();
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    gMap = googleMap;
                    gMap.getUiSettings().setZoomControlsEnabled(true);
                    getLocation();
                }
            });


            btnCanel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            btnStore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Lokacija lokacija = new Lokacija();

                    if(!et_title.getText().toString().equals("") && !et_description.getText().toString().equals("")) {

                        lokacija.setLocationName(et_title.getText().toString());
                        lokacija.setDescription(et_description.getText().toString());
                        lokacija.setDate(tv_date.getText().toString());
                        lokacija.setLatitude(latitude);
                        lokacija.setLongitude(longitude);

                        reference.push().setValue(lokacija);

                        finish();

                    }
                    Toast toast = Toast.makeText(getApplicationContext(),"Fill out both fields", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });






        }


        private void updateMap(LatLng latLng){
            gMap.addMarker(new MarkerOptions().position(latLng).title("Here we are!"));
            gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            //animacija zomiranja
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
            et_title.setEnabled(true);
            et_description.setEnabled(true);

        }

        @SuppressLint("MissingPermission")
        private void getLocation(){
            if(!hasAnyFeature(PackageManager.FEATURE_LOCATION,PackageManager.FEATURE_LOCATION_GPS,PackageManager.FEATURE_LOCATION_NETWORK)){
                Toast toast = Toast.makeText(getApplicationContext(),"No feature available",Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            if(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)){
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if(task.isSuccessful()){
                            Location location = task.getResult();
                            if(location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                                LatLng latLng = new LatLng(latitude, longitude);
                                updateMap(latLng);
                            }else{
                                finish();
                            }
                        }else{
                            Toast toast = Toast.makeText(getApplicationContext(),"Something went wrong!",Toast.LENGTH_SHORT);
                            toast.show();
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
                        toast.show();
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


