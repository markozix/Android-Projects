package com.example.rma_project2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity {

    private EditText et_email;
    private EditText et_password;

    private Button btnLogIn;
    private Button btnSignUp;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initFirebase();
        init();
    }


    public void init(){



        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.password);

        btnLogIn = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignin);





            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firebaseAuth.createUserWithEmailAndPassword(et_email.getText().toString(), et_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LogInActivity.this, "SignUp failed!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(LogInActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            btnLogIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firebaseAuth.signInWithEmailAndPassword(et_email.getText().toString(), et_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LogInActivity.this,"Authentication failed!", Toast.LENGTH_SHORT).show();
                            }else{
                                Intent intent = new Intent(LogInActivity.this, BaseFragment.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            });
        }





    public void initFirebase(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }

}
