package com.example.rma_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
        initFirebaseAuth();


    }



    public void initFirebaseAuth(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void init(){


        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.logo2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    if(firebaseAuth.getCurrentUser() != null) {
                        Intent i = new Intent(SplashActivity.this, LoggedUserActivity.class);
                        startActivity(i);

                    }else{
                        Intent i = new Intent(SplashActivity.this, LogInActivity.class);
                        startActivity(i);

                    }

                    finish();


            }
        }, 1300);



    }




}
