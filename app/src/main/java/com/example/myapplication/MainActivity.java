package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup,signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        signup=findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        DB =new DBHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(MainActivity.this, "All fields Required", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (!checkuser) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert) {
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                                Toast.makeText(MainActivity.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
        });
        signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
           startActivity(intent);
           }
        });
    }
}