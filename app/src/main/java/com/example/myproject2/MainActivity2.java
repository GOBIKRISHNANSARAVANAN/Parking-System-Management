package com.example.myproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
Spinner spinner;
EditText txt1,txt2,txt3;
Button button;
String item;
String available_slot;
String vehicletype,datein,timein,vehicleno;
    ArrayList<Integer> emptylist;
    String valuetofind,valuetofind1,id;
    total total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        spinner=(Spinner)findViewById(R.id.spinner);
        txt1=(EditText)findViewById(R.id.ac2txt1);
        txt2=(EditText)findViewById(R.id.ac2txt2);
        txt3=(EditText)findViewById(R.id.ac2txt3);
        button=(Button) findViewById(R.id.ac2btn1);


        DatabaseReference reff=FirebaseDatabase.getInstance().getReference().child("Current");
        DatabaseReference refft=FirebaseDatabase.getInstance().getReference().child("Total");




        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = sdf1.format(new Date());
        txt2.setText(currentDate);
        SimpleDateFormat sdf2=new SimpleDateFormat("hh : mm : ss");
        String currentTime=sdf2.format(new Date());
        txt3.setText(currentTime);
        SimpleDateFormat sdf3=new SimpleDateFormat("HH mm ss");
        String verify=sdf3.format(new Date());
        int hour=Integer.parseInt(verify.substring(0,2));
        String ampm;
        if(hour<=12){
            ampm="AM";
        }else{
            ampm="PM";
        }
        txt3.setText(txt3.getText().toString()+" "+ampm);



        List<String> list=new ArrayList<>();

        list.add("Select");
        list.add("LMV");
        list.add("Mini Passenger Vehicles");
        list.add("Passenger Vehicles");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicletype = item;
                vehicleno = txt1.getText().toString();
                datein = txt2.getText().toString();
                timein = txt3.getText().toString();
                reff.orderByChild("no").equalTo("").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String key;
                        ArrayList<Integer> emptylist=new ArrayList<>();
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            key = ds.getKey();
                            emptylist.add(Integer.parseInt(key));
                        }
                        if(emptylist.isEmpty()){
                            Toast.makeText(MainActivity2.this,"No available slots",Toast.LENGTH_LONG).show();

                        }else{
                            valuetofind=String.valueOf(emptylist.get(0));
                            DatabaseReference db=FirebaseDatabase.getInstance().getReference();
                            db.child("Current").orderByChild("no").equalTo(vehicleno).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        Toast.makeText(MainActivity2.this,"Vehicle exists",Toast.LENGTH_LONG).show();
                                        for(DataSnapshot ds1:snapshot.getChildren()){
                                            valuetofind1=ds1.getKey();
                                        }

                                        refft.orderByChild("vehicleno").equalTo(vehicleno).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){
                                                    for(DataSnapshot ds : snapshot.getChildren()) {
                                                        id = ds.getKey();

                                                    }

                                                    Toast.makeText(MainActivity2.this,id,Toast.LENGTH_LONG).show();
                                                    HashMap hashMap1=new HashMap();
                                                    hashMap1.put("dateandtimeout"," "+datein+" "+timein);
                                                    refft.child(id).updateChildren(hashMap1).addOnSuccessListener(new OnSuccessListener() {
                                                        @Override
                                                        public void onSuccess(Object o) {

                                                        }
                                                    });



                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                        update();

                                    }
                                    else{
                                        Toast.makeText(MainActivity2.this,"Vehicle not exists",Toast.LENGTH_LONG).show();
                                        DatabaseReference db1=FirebaseDatabase.getInstance().getReference();


                                        HashMap hashMap1=new HashMap();
                                        hashMap1.put("no",vehicleno);
                                        db1.child("Current").child(valuetofind).updateChildren(hashMap1).addOnSuccessListener(new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {

                                            }
                                        });

                                        HashMap hashMap2=new HashMap();
                                        hashMap2.put("type",vehicletype);
                                        db1.child("Current").child(valuetofind).updateChildren(hashMap2).addOnSuccessListener(new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {

                                            }
                                        });

                                        HashMap hashMap3=new HashMap();
                                        hashMap3.put("date",datein);
                                        db1.child("Current").child(valuetofind).updateChildren(hashMap3).addOnSuccessListener(new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {

                                            }
                                        });

                                        HashMap hashMap4=new HashMap();
                                        hashMap4.put("time",timein);
                                        db1.child("Current").child(valuetofind).updateChildren(hashMap4).addOnSuccessListener(new OnSuccessListener() {
                                            @Override
                                            public void onSuccess(Object o) {

                                            }
                                        });
                                        total=new total();
                                        total.setDatein(datein);
                                        total.setTimein(timein);
                                        total.setVehicleno(vehicleno);
                                        total.setSlotno(String.valueOf(valuetofind));
                                        total.setType(vehicletype);
                                        total.setDateandtimeout("");
                                        refft.push().setValue(total);



                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        });
    }

    private void update() {
        DatabaseReference db1=FirebaseDatabase.getInstance().getReference();

        HashMap hashMap1=new HashMap();
        hashMap1.put("no","");
        db1.child("Current").child(valuetofind1).updateChildren(hashMap1).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {

            }
        });

        HashMap hashMap2=new HashMap();
        hashMap2.put("type","");
        db1.child("Current").child(valuetofind1).updateChildren(hashMap2).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {

            }
        });

        HashMap hashMap3=new HashMap();
        hashMap3.put("date","");
        db1.child("Current").child(valuetofind1).updateChildren(hashMap3).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {

            }
        });

        HashMap hashMap4=new HashMap();
        hashMap4.put("time","");
        db1.child("Current").child(valuetofind1).updateChildren(hashMap4).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {

            }
        });

    }
}