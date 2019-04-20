package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubjectTimings extends AppCompatActivity {
    String[] subjectArray = {"Accounting [ACCT]","Anthropology [ANTH]","Arabic [ARAB]","Arts Management [AMGT]",
            "Assistive Technology [EDAT]","Astronomy [ASTR]"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_timings);



        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_subject_timing_list, subjectArray);

        ListView listView = (ListView) findViewById(R.id.timing_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Display the selected item text on TextView
                Intent reg = new Intent(SubjectTimings.this, SubjectList.class);

                reg.putExtra("index", String.valueOf(position));
                startActivity(reg);
            }
        });

    }
}
