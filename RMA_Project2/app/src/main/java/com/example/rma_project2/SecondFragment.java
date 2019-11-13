package com.example.rma_project2;

import adapteri.ChatItemAdapter;
import adapteri.RasporedItemAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import model.ChatItem;
import model.Message;
import viewmodel.MainViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SecondFragment extends Fragment {


    private MainViewModel mainViewModel;
    private ChatItemAdapter adapter;
    private DatabaseReference reference;
    private ValueEventListener valueEventListener;
    private ChildEventListener childEventListener;
    private ArrayList<ChatItem> listaItema = new ArrayList<>();
    private LinearLayoutManager manager;

    public static SecondFragment newInstance(String arg) {return new SecondFragment();}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_second_fragment, container,false);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);



        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewFR2);

        manager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(manager);



        adapter = new ChatItemAdapter();


        adapter.setOnItemClickedCallback(new ChatItemAdapter.OnItemClickedCallback() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getContext(), ChatActivity.class);

                String name = listaItema.get(position).getName();
                String id = listaItema.get(position).getId();

                intent.putExtra("name",name);
                intent.putExtra("id", id);

                startActivity(intent);
            }
        });


        recyclerView.setAdapter(adapter);

        return view;

    }
    public void initFirebase(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Chat");

        //ChatItem item = new ChatItem();
       // item.setName("TestName");
       // item.setMsg(new Message("poruka"));

       // reference.push().setValue(item);


        createListeners();

    }

    private void createListeners(){

        valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaItema.clear();

                if(dataSnapshot.getValue() == null){
                    return;
                }

                for(DataSnapshot child : dataSnapshot.getChildren()){
                    ChatItem item = child.getValue(ChatItem.class);
                    String key = child.getKey();
                    item.setId(key);

                    listaItema.add(item);

                }

                adapter.setData(listaItema);

                int lastIndex = listaItema.size() - 1;
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
                Toast toast = Toast.makeText(getContext(),"Child added ", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast toast = Toast.makeText(getContext(),"Child changed ", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Toast toast = Toast.makeText(getContext(),"Child removed", Toast.LENGTH_SHORT);
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
    public void onDestroy() {
        super.onDestroy();
        reference.removeEventListener(valueEventListener);
        reference.removeEventListener(childEventListener);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initFirebase();





    }
}
