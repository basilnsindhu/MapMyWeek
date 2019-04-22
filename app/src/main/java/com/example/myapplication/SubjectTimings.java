package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class SubjectTimings extends AppCompatActivity {
    String[] subjectArray = {"Accounting[ACCT]", "African & Af-American Studies [AFAM]", "Anthropology [ANTH]", "Applied Information Technology [AIT]", "Arabic [ARAB]", "Art History [ARTH]", "Art and Visual Technology [AVT]",
            "Arts Management [AMGT]", "Assistive Technology [EDAT]", "Athletic Training Educ Program [ATEP]", "BA in Applied Science [BAS]", "Bachelor Individualized Study [BIS]",
            "Bioengineering [BENG]", "Biology [BIOL]", "Biosciences [BIOS]", "College of Science [COS]", "Computer Game Design [GAME]", "Computer Science [CS]", "Cyber Security Engineering [CYSE]",
            "Data Analytics Engineering [DAEN]", "Design [DSGN]", "Electrical & Computer Enginrg [ECE]", "Engineering [ENGR]", "Finance [FNAN]", "Forensic Science [FRSC]", "Geology [GEOL]",
            "Government [GOVT]", "Information Technology [IT]", "Software Engineering [SWE]", "Statistics [STAT]", "System Engineering [SYST]"};

    EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_timings);



        final ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_subject_timing_list, subjectArray);

        final ListView listView = (ListView) findViewById(R.id.timing_list);
        final ArrayAdapter orig_adap =  new ArrayAdapter<String>(this, R.layout.activity_subject_timing_list, subjectArray);
        listView.setAdapter(adapter);


        EditText inputSearch = findViewById(R.id.inputSearch);


        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                int temp = orig_adap.getPosition(parent.getItemAtPosition(position));
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Display the selected item text on TextView
                Intent reg = new Intent(SubjectTimings.this, SubjectList.class);

                reg.putExtra("index", temp);
                startActivity(reg);
            }
        });

    }


}
