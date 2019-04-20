package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

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
    private NotificationHelper mNotificationHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mNotificationHelper = new NotificationHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        final String positionBack = getIntent().getStringExtra("index");
        dbHelper = new DBHelper(getApplicationContext());
        SubjectClass obj = new SubjectClass();
        obj.setEndTime("10:10");
        obj.setInstructor("Kershberg");
        obj.setStarttime("07:20");
        obj.setDays("W");
        obj.setLocation("Enterprise Hall 178");
        obj.setTitle("Database Concepts");
        obj.setLat("37.0902");
        obj.setLongi("95.7129");
        obj.setAlarmID(0);

        SubjectClass obj1 = new SubjectClass();
        obj1.setEndTime("07:10");
        obj1.setInstructor("Sood");
        obj1.setStarttime("05:25");
        obj1.setDays("RS");
        obj1.setLocation("Sandbridge Hall 107");
        obj1.setTitle("Security Enginerring");
        obj1.setLat("37.0902");
        obj1.setLongi("95.7129");
        obj1.setAlarmID(100);
        subject.add(obj);
        subject.add(obj1);




        SubjectClass obj3 = new SubjectClass();
        obj3.setEndTime("04:00");
        obj3.setInstructor("Cheng");
        obj3.setStarttime("05:30");
        obj3.setLocation("Innovation Hall 132");
        obj3.setTitle("Operating System");
        obj3.setDays("MW");
        obj3.setLat("37.0902");
        obj3.setLongi("95.7129");
        obj3.setAlarmID(300);

        SubjectClass obj4 = new SubjectClass();
        obj4.setEndTime("02:10");
        obj4.setInstructor("Bell");
        obj4.setStarttime("3:00");
        obj4.setDays("F");
        obj4.setLocation("Exploratory Hall L003");
        obj4.setTitle("Concurrent and Distrib Systems");
        obj4.setLat("37.0902");
        obj4.setLongi("95.7129");
        obj4.setAlarmID(400);
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
                    calendarList.add(subject.get(position));
                    setAlarm(subject.get(position).getStarttime(), subject.get(position).getDays(), subject.get(position).getTitle(), subject.get(position).getLocation(), subject.get(position).getAlarmID());
                    Intent intent = new Intent(getApplicationContext(),AddRemoveActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(Integer.valueOf(positionBack)==1)
                {
                    Toast.makeText(getApplicationContext(),"Subject is added Successfuly",Toast.LENGTH_SHORT).show();
                    dbHelper.insertSUBJECT(subject1.get(position).getTitle(), subject1.get(position).getInstructor(),subject1.get(position).getStarttime(), subject1.get(position).getEndTime(), subject1.get(position).getDays(), subject1.get(position).getLat(), subject1.get(position).getLongi() , subject1.get(position).getLocation());
                    calendarList.add(subject1.get(position));
                    setAlarm(subject1.get(position).getStarttime(), subject1.get(position).getDays(), subject1.get(position).getTitle(), subject1.get(position).getLocation(), subject1.get(position).getAlarmID());
                    Intent intent = new Intent(getApplicationContext(),AddRemoveActivity.class);
                    startActivity(intent);
                    finish();
                }
                //basic.onMonthChange(Calendar.YEAR,Calendar.MONTH);basic.addtolist(calendarList);
                //basic.onMonthChange(Calendar.YEAR,Calendar.MONTH);

            }
        });
    }
    // Probably going to set the alarm from here
     private void setAlarm(String time, String sDay, String title, String location, int ID) {

        String[] timeArr = time.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        if (hour <= 8) {
            hour += 12;
        }
        int minute = Integer.parseInt(timeArr[1]);
        int[] numDays = {1, 1, 1};
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        String[] notificationData = {time, title, location};
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("notificationData", notificationData);
        intent.putExtra("AlarmID", ID);
        for (int i = 0; i < sDay.length(); i++) {
            if (sDay.charAt(i) == 'M') {
                ID += 2;
                numDays[i] = 2;
            } else if (sDay.charAt(i) == 'T') {
                ID += 3;
                numDays[i] = 3;
            } else if (sDay.charAt(i) == 'W') {
                ID += 4;
                numDays[i] = 4;
            } else if (sDay.charAt(i) == 'R') {
                ID += 5;
                numDays[i] = 5;
            } else if (sDay.charAt(i) == 'F') {
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
}
