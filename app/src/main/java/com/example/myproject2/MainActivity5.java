package com.example.myproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity5 extends AppCompatActivity {
Button button1,button2,button3;
EditText text1,text2;
    long maxid=0;
String value1,value2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        text1=(EditText)findViewById(R.id.no);
        text2=(EditText)findViewById(R.id.no1);
        Current current=new Current();

        DatabaseReference reff=FirebaseDatabase.getInstance().getReference().child("Current");
        reff.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                maxid = (long) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1=text1.getText().toString();
                DatabaseReference refference= FirebaseDatabase.getInstance().getReference().child("Current");
                String date,time,id,no,type;
                for(int i = 1; i<=Integer.parseInt(value1);i++){
                    current.setId(String.valueOf(i));
                    current.setDate("");
                    current.setNo("");
                    current.setType("");
                    current.setTime("");
                    refference.child(String.valueOf(i)).setValue(current);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value2=text2.getText().toString();
                DatabaseReference refference=FirebaseDatabase.getInstance().getReference().child("Current").child(value2.toString());
                refference.removeValue();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}