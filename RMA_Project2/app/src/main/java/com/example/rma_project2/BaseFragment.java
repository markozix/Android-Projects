package com.example.rma_project2;

import adapteri.SimplePageAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class BaseFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment);
        init();
    }



    public void init(){

        ViewPager viewPager = findViewById(R.id.viewPager_main);
        SimplePageAdapter adapter = new SimplePageAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



    }

}
