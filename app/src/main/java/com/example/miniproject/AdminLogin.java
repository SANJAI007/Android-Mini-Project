package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    Button login;
    DBhelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        login = (Button) findViewById(R.id.login);
        db=new DBhelper(this);
        //db.insertAdmin("saravana","saravana");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid =((EditText)findViewById(R.id.userid)).getText().toString();
                String pass =((EditText)findViewById(R.id.password)).getText().toString();
                Boolean boolValidate=db.validateAdmin(uid,pass);
                if(boolValidate==true) {
                    Toast.makeText(getApplicationContext(),"Login Successfull !",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), AdminHome.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid UserId / Password",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}