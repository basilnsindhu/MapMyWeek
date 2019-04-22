package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class SubjectList extends AppCompatActivity {
    ArrayList<ArrayList<SubjectClass>> fullList = new ArrayList<ArrayList<SubjectClass>>();
    ArrayList<SubjectClass> game = new ArrayList<>(); //16
    ArrayList<SubjectClass> cs = new ArrayList<>(); //17
    ArrayList<SubjectClass> swe = new ArrayList<>(); //27
    ArrayList<SubjectClass> ece = new ArrayList<>(); //21
    ArrayList<SubjectClass> syst = new ArrayList<>(); //29
    ArrayList<SubjectClass> def = new ArrayList<>();

    ArrayList<SubjectClass> calendarList = new ArrayList<>();
    BasicActivity basic = new BasicActivity();
    DBHelper dbHelper;
    SubjectsAdapter adapter;
    //private NotificationHelper mNotificationHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // mNotificationHelper = new NotificationHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        final int positionBack = getIntent().getIntExtra("index", 0);
        dbHelper = new DBHelper(getApplicationContext());
        initialize();



        switch (positionBack){
            case 16: //game
                adapter = new SubjectsAdapter(getApplicationContext(),game);
                break;
            case 17: //cs
                adapter = new SubjectsAdapter(getApplicationContext(),cs);
                break;
            case 21: //cs
                adapter = new SubjectsAdapter(getApplicationContext(),ece);
                break;
            case 27: //cs
                adapter = new SubjectsAdapter(getApplicationContext(),swe);
                break;
            case 29: //cs
                adapter = new SubjectsAdapter(getApplicationContext(),syst);
                break;
        }
        ListView listView = (ListView) findViewById(R.id.subject_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Subject is added Successfuly",Toast.LENGTH_SHORT).show();
                SubjectClass temp = (SubjectClass) adapter.getItem(position);
                dbHelper.insertSUBJECT(temp.getTitle(), temp.getInstructor(),temp.getStarttime(), temp.getEndTime(), temp.getDays(), temp.getLat(), temp.getLongi() , temp.getLocation());
                setAlarm(temp.getStarttime(),temp.getDays(), temp.getTitle(), temp.getLocation(), temp.getAlarmID());
                Intent intent = new Intent(SubjectList.this,AddRemoveActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    // Sets an alarm for the start time of a class on whatever day its on
    // - Jared
     private void setAlarm(String time, String sDay, String title, String location, int ID) {

        String[] timeArr = time.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int minute = Integer.parseInt(timeArr[1]);
        int[] numDays = {1, 1, 1};
        String[] daysplit = sDay.split("-");
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        String[] notificationData = {time, title, location};
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("notificationData", notificationData);
        intent.putExtra("AlarmID", ID);
        for (int i = 0; i < daysplit.length; i++) {
            if (daysplit[i].charAt(0) == 'M') {
                ID += 2;
                numDays[i] = 2;
            } else if (daysplit[i].charAt(0) == 'T') {
                ID += 3;
                numDays[i] = 3;
            } else if (daysplit[i].charAt(0) == 'W') {
                ID += 4;
                numDays[i] = 4;
            } else if (daysplit[i].charAt(0) == 'R') {
                ID += 5;
                numDays[i] = 5;
            } else if (daysplit[i].charAt(0) == 'F') {
                ID += 6;
                numDays[i] = 6;
            } else {
                ID += 7;
                numDays[i] = 7;
            }
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, ID, intent, 0);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.DAY_OF_WEEK, numDays[i]);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7,pendingIntent);
        }

     }


     private void initialize(){




        SubjectClass obj = new SubjectClass();
        obj.setEndTime("22:10");
        obj.setInstructor("Kershberg");
        obj.setStarttime("19:20");
        obj.setDays("W");
        obj.setLocation("Enterprise Hall 178");
        obj.setTitle("CS450 - Database Concepts");
        obj.setLat("37.0902");
        obj.setLongi("95.7129");
        obj.setAlarmID(0);
        cs.add(obj);

        SubjectClass obj1 = new SubjectClass();
        obj1.setEndTime("19:10");
        obj1.setInstructor("Sood");
        obj1.setStarttime("16:30");
        obj1.setDays("R");
        obj1.setLocation("Sandbridge Hall 107");
        obj1.setTitle("Security Enginerring");
        obj1.setLat("37.0902");
        obj1.setLongi("95.7129");
        obj1.setAlarmID(100);
        cs.add(obj1);

        SubjectClass obj3 = new SubjectClass();
        obj3.setEndTime("16:00");
        obj3.setInstructor("Cheng");
        obj3.setStarttime("15:15");
        obj3.setLocation("Innovation Hall 132");
        obj3.setTitle("Operating System");
        obj3.setDays("M-W");
        obj3.setLat("38.828630");
        obj3.setLongi("-77.307440");
        obj3.setAlarmID(300);
         cs.add(obj3);

        SubjectClass obj4 = new SubjectClass();
        obj4.setEndTime("14:10");
        obj4.setInstructor("Bell");
        obj4.setStarttime("12:00");
        obj4.setDays("F");
        obj4.setLocation("Exploratory Hall L003");
        obj4.setTitle("Concurrent and Distrib Systems");
        obj4.setLat("37.0902");
        obj4.setLongi("95.7129");
        obj4.setAlarmID(400);
        cs.add(obj4);

        fullList.add(cs);

     }
}
