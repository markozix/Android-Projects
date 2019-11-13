package com.example.projekat1;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapteri.TrosakAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import model.Trosak;
import viewModel.MainViewModel;

public class DetalisActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalis);


        TextView tvName = findViewById(R.id.tv_name);
        TextView tvCost = findViewById(R.id.tv_cost);
        TextView tvCategory = findViewById(R.id.tv_category);
        TextView tvDate = findViewById(R.id.tv_date);
        Button btnRemove = findViewById(R.id.btnRemove);
        ImageView ivSlika = findViewById(R.id.iv_slika);
        ivSlika.setImageResource(R.drawable.galaksija);


        Intent i = getIntent();

        tvName.setText(i.getStringExtra("name"));
        tvCost.setText(i.getStringExtra("cost"));
        tvCategory.setText(i.getStringExtra("category"));
        tvDate.setText(i.getStringExtra("date"));



        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.putExtra("position", i.getIntExtra("position", 0));
                setResult(Activity.RESULT_OK,intent);
                finish();

            }
        });




    }
}
