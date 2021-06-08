package com.example.myproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    currentadapter adapter;
    ArrayList<Current> currentlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Current");
        recyclerView=(RecyclerView)findViewById(R.id.ac3recyclerview);
        databaseReference= FirebaseDatabase.getInstance().getReference("Current");
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        currentlist=new ArrayList<>();
        adapter=new currentadapter(this,currentlist);
        recyclerView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    Current current=snapshot1.getValue(Current.class);
                    currentlist.add(current);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}