package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SubjectList extends AppCompatActivity {
    // Array of strings...
    String[] mobileArray = {"Accounting [ACCT]","Anthropology [ANTH]","Arabic [ARAB]","Arts Management [AMGT]",
            "Assistive Technology [EDAT]","Astronomy [ASTR]"};
    ArrayList<SubjectClass> subject = new ArrayList<>();
    ArrayList<SubjectClass> subject1 = new ArrayList<>();
    ArrayList<SubjectClass> calendarList = new ArrayList<>();
    BasicActivity basic = new BasicActivity();
    DBHelper dbHelper;
    SubjectsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        final String positionBack = getIntent().getStringExtra("index");
        dbHelper = new DBHelper(getApplicationContext());
        SubjectClass obj = new SubjectClass();
        obj.setEndTime("22:10");
        obj.setInstructor("Kershberg");
        obj.setStarttime("19:20");
        obj.setDays("W");
        obj.setLocation("Enterprise Hall 178");
        obj.setTitle("Database Concepts");
        obj.setLat("37.0902");
        obj.setLongi("95.7129");

        SubjectClass obj1 = new SubjectClass();
        obj1.setEndTime("19:10");
        obj1.setInstructor("Sood");
        obj1.setStarttime("16:30");
        obj1.setDays("R");
        obj1.setLocation("Sandbridge Hall 107");
        obj1.setTitle("Security Enginerring");
        obj1.setLat("37.0902");
        obj1.setLongi("95.7129");
        subject.add(obj);
        subject.add(obj1);




        SubjectClass obj3 = new SubjectClass();
        obj3.setEndTime("16:00");
        obj3.setInstructor("Cheng");
        obj3.setStarttime("15:15");
        obj3.setLocation("Innovation Hall 132");
        obj3.setTitle("Operating System");
        obj3.setDays("M-W");
        obj3.setLat("38.828630");
        obj3.setLongi("-77.307440");

        SubjectClass obj4 = new SubjectClass();
        obj4.setEndTime("14:10");
        obj4.setInstructor("Bell");
        obj4.setStarttime("12:00");
        obj4.setDays("F");
        obj4.setLocation("Exploratory Hall L003");
        obj4.setTitle("Concurrent and Distrib Systems");
        obj4.setLat("37.0902");
        obj4.setLongi("95.7129");
        subject1.add(obj3);
        subject1.add(obj4);
        if(Integer.valueOf(positionBack)==0){
            adapter =new SubjectsAdapter(getApplicationContext(),subject);
        }
        if(Integer.valueOf(positionBack)==1){
            adapter =new SubjectsAdapter(getApplicationContext(),subject1);
        }
        ListView listView = (ListView) findViewById(R.id.subject_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
               // String selectedItem = (String) parent.getItemAtPosition(position);

                if(Integer.valueOf(positionBack)==0)
                {
                    Toast.makeText(getApplicationContext(),"Subject is added Successfuly",Toast.LENGTH_SHORT).show();
                    dbHelper.insertSUBJECT(subject.get(position).getTitle(), subject.get(position).getInstructor(),subject.get(position).getStarttime(), subject.get(position).getEndTime(), subject.get(position).getDays(), subject.get(position).getLat(), subject.get(position).getLongi() , subject.get(position).getLocation());
                    //.add(subject.get(position));
                    Intent intent = new Intent(getApplicationContext(),AddRemoveActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(Integer.valueOf(positionBack)==1)
                {
                    Toast.makeText(getApplicationContext(),"Subject is added Successfuly",Toast.LENGTH_SHORT).show();
                    dbHelper.insertSUBJECT(subject1.get(position).getTitle(), subject1.get(position).getInstructor(),subject1.get(position).getStarttime(), subject1.get(position).getEndTime(), subject1.get(position).getDays(), subject1.get(position).getLat(), subject1.get(position).getLongi() , subject1.get(position).getLocation());
                    //calendarList.add(subject1.get(position));
                    Intent intent = new Intent(getApplicationContext(),AddRemoveActivity.class);
                    startActivity(intent);
                    finish();
                }
                //basic.onMonthChange(Calendar.YEAR,Calendar.MONTH);basic.addtolist(calendarList);
               // basic.onMonthChange(Calendar.YEAR,Calendar.MONTH);

            }
        });
    }
}
