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

public class MainActivity4 extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    totaladapter adapter;
    ArrayList<total> totallist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Total");
        recyclerView=(RecyclerView)findViewById(R.id.ac4recyclerview);
        databaseReference= FirebaseDatabase.getInstance().getReference("Total");
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        totallist=new ArrayList<>();
        adapter=new totaladapter(this,totallist);
        recyclerView.setAdapter(adapter);
        databaseReference.orderByChild("dateandtimeout").startAt(" ").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    total total=snapshot1.getValue(total.class);
                    totallist.add(total);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}