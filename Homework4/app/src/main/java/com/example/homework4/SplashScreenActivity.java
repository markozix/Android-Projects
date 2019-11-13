package com.example.homework4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        init();
    }

    private void init(){


        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(i);
            }
        }, 1500);



    }


}
