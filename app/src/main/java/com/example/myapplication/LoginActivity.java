package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button signup,signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signup =findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        DB =new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String pass= password.getText().toString();

                if(TextUtils.isEmpty(user)|| TextUtils.isEmpty(pass))
                    Toast.makeText(LoginActivity.this,"All fields Required", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass= DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"Login failed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}