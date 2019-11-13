package com.example.rma_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoggedUserActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView tv_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_user);
        initFirebaseAuth();
        init();


    }

    public void initFirebaseAuth(){

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void init(){



        tv_user = findViewById(R.id.textView);

        tv_user.setText("Logged in as:");

        TextView tv_email = findViewById(R.id.textView2);
        tv_email.setText(firebaseAuth.getCurrentUser().getEmail());

        Button btnContinue = findViewById(R.id.contunue);
        Button btnLogOut = findViewById(R.id.LogOut);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(LoggedUserActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedUserActivity.this,BaseFragment.class);
                startActivity(intent);
                finish();
            }
        });


    }


}
