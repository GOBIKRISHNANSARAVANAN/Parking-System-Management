package com.example.myproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText txt1,txt2;
String username,password;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1=(EditText)findViewById(R.id.user_name);
        txt2=(EditText)findViewById(R.id.pass_word);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=txt1.getText().toString();
                String password=txt2.getText().toString();
                if(TextUtils.isEmpty(username)&&TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this,"Enter the login credentials",Toast.LENGTH_LONG).show();
                }else if(username.equals("Current details")&&password.equals("currentdetails")){
                    Intent intent=new Intent(MainActivity.this,MainActivity3.class);
                    startActivity(intent);
                }else if(username.equals("general")&&password.equals("general")){
                    Intent intent1=new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent1);
                }else if(username.equals("total")&&password.equals("total")){
                    Intent intent2=new Intent(MainActivity.this,MainActivity4.class);
                    startActivity(intent2);
                }else if(username.equals("admin")&&password.equals("admin")){
                    Intent intent3=new Intent(MainActivity.this,MainActivity5.class);
                    startActivity(intent3);
                }
            }
        });
    }
}