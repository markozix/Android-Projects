package com.example.rma_project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import model.ChatItem;
import model.Message;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private DatabaseReference reference;
    private String name;
    private TextView tv_name;
    private String id;
    private ArrayList<String> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        init();
    }

    private void init(){

        initFir();
        tv_name = findViewById(R.id.tv_name);
        getInten();
        EditText et_text = findViewById(R.id.et_msg);

        RecyclerView recyclerView = findViewById(R.id.recyclerChat);

        LinearLayoutManager manager = new LinearLayoutManager();

        recyclerView.setLayoutManager(manager);



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    if(child.getChildren().equals(id)){
                        ChatItem item = child.getValue(ChatItem.class);
                        item.setMsg(new Message(et_text.getText().toString()));


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void getInten(){

        Intent i = getIntent();
        name = i.getStringExtra("name");
        tv_name.setText(name);
        id = i.getStringExtra("id");

       // ChatItem item = new ChatItem();
       // item.setMsg(new Message("sadasdasda"));
       // reference.push().setValue(item);



    }


    private void initFir(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


        reference = firebaseDatabase.getReference().child("Chat");
    }






















}
