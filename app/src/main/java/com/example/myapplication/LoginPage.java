package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    Button register;
    TextView info;
    int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.etusername);
        password = findViewById(R.id.etpassword);
        login = findViewById(R.id.btnlogin);
        register = findViewById(R.id.btnreg);
        info = findViewById(R.id.tvinfo);
        info.setText("Attempts remaining: 5");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(),password.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(LoginPage.this, Register.class);
                startActivity(reg);
            }
        });
    }

    private void validate(String string1, String string2){
        if(string1.equals("admin") && string2.equals("login123")){
            Intent login = new Intent(LoginPage.this, MainActivity.class);
            startActivity(login);
        }

        else{
            counter --;

            info.setText("Attempts remaining: "+ counter);

            if(counter == 0){
                login.setEnabled(false);
            }
        }
    }
}
