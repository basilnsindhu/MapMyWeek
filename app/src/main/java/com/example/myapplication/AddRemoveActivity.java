package com.example.myapplication;
/*
Neel patel
    CS 321-006 Team 4
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddRemoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove);

        Button add = findViewById(R.id.btn_add_class);
        Button home = findViewById(R.id.btn_home);
        Button remove = findViewById(R.id.btn_remove_class);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddRemoveActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddRemoveActivity.this,SubjectTimings.class);
                startActivity(intent);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddRemoveActivity.this,DeleteActivity.class);
                startActivity(intent);
//                addNotification();
            }
        });
    }
}
