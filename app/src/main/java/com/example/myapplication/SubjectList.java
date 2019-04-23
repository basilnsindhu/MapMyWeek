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
    ArrayList<SubjectClass> swe = new ArrayList<>(); //28
    ArrayList<SubjectClass> ece = new ArrayList<>(); //21
    ArrayList<SubjectClass> syst = new ArrayList<>(); //30
    ArrayList<SubjectClass> math = new ArrayList<>(); //27

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
            case 21: //ece
                adapter = new SubjectsAdapter(getApplicationContext(),ece);
                break;
            case 28: //swe
                adapter = new SubjectsAdapter(getApplicationContext(),math);
                break;
            case 29: //swe
                adapter = new SubjectsAdapter(getApplicationContext(),swe);
                break;
            case 31: //syst
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
     private void setAlarm(String time, String sDay, String title, String location, int alarmID) {
        int ID = alarmID * 100;
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



        //CS
        SubjectClass obj = new SubjectClass();
        obj.setEndTime("22:00");//22:00
        obj.setInstructor("Kershberg");
        obj.setStarttime("19:20");//19:20
        obj.setDays("W");//W
        obj.setLocation("Enterprise Hall 178");
        obj.setTitle("CS450 - Database Concepts");
        obj.setLat("38.829032");
        obj.setLongi("-77.306368");
        obj.setAlarmID(0);
        cs.add(obj);

        SubjectClass obj1 = new SubjectClass();
        obj1.setEndTime("19:10");//19:10
        obj1.setInstructor("Sood");
        obj1.setStarttime("16:30");//16:30
        obj1.setDays("R");//R
        obj1.setLocation("Sandbridge Hall 107");
        obj1.setTitle("CS469 - Security Enginerring");
        obj1.setLat("38.832508");
        obj1.setLongi("-77.306490");
        obj1.setAlarmID(1);
        cs.add(obj1);

        SubjectClass obj3 = new SubjectClass();
        obj3.setEndTime("14:45");
        obj3.setInstructor("Cheng");
        obj3.setStarttime("13:30");
        obj3.setLocation("Innovation Hall 132");
        obj3.setTitle("CS471 - Operating System");
        obj3.setDays("T-R");
        obj3.setLat("38.828630");
        obj3.setLongi("-77.307440");
        obj3.setAlarmID(2);
        cs.add(obj3);

        SubjectClass obj4 = new SubjectClass();
        obj4.setEndTime("10:15");
        obj4.setInstructor("Bell");
        obj4.setStarttime("09:00");
        obj4.setDays("M-W");
        obj4.setLocation("Exploratory Hall L003");
        obj4.setTitle("CS475 - Concurrent and Distrib Systems");
        obj4.setLat("38.829686");
        obj4.setLongi("-77.305506");
        obj4.setAlarmID(3);
        cs.add(obj4);




        //ECE
         SubjectClass obj5 = new SubjectClass();
         obj5.setEndTime("13:15");
         obj5.setInstructor("Lorie");
         obj5.setStarttime("12:00");
         obj5.setDays("T-R");
         obj5.setLocation("Planetary Hall 129");
         obj5.setTitle("ECE101 - Intro to Electrical/Comp Engr");
         obj5.setLat("38.829898");
         obj5.setLongi("-77.306281");
         obj5.setAlarmID(4);
         ece.add(obj5);

         SubjectClass obj6 = new SubjectClass();
         obj6.setEndTime("11:45");
         obj6.setInstructor("Paris");
         obj6.setStarttime("10:30");
         obj6.setDays("T-R");
         obj6.setLocation("Exploratory L004");
         obj6.setTitle("ECE201 - Intro to Signals and Systems");
         obj6.setLat("38.829686");
         obj6.setLongi("-77.305506");
         obj6.setAlarmID(5);
         ece.add(obj6);

         SubjectClass obj7 = new SubjectClass();
         obj7.setEndTime("11:45");
         obj7.setInstructor("Nelson");
         obj7.setStarttime("10:30");
         obj7.setDays("T-R");
         obj7.setLocation("Innovation 204");
         obj7.setTitle("ECE220 - Continuos Time Signals and Systems");
         obj7.setLat("38.828630");
         obj7.setLongi("-77.307440");
         obj7.setAlarmID(6);
         ece.add(obj7);

         SubjectClass obj8 = new SubjectClass();
         obj8.setEndTime("16:15");
         obj8.setInstructor("Li");
         obj8.setStarttime("15:00");
         obj8.setDays("T-R");
         obj8.setLocation("East 201");
         obj8.setTitle("ECE305 - Electromagnetic Theory");
         obj8.setLat("38.832995");
         obj8.setLongi("-77.308255");
         obj8.setAlarmID(7);
         ece.add(obj8);


         SubjectClass obj9 = new SubjectClass();
         obj9.setEndTime("10:15");
         obj9.setInstructor("Lorie");
         obj9.setStarttime("9:00");
         obj9.setDays("T-R");
         obj9.setLocation("Lecture Hall 1");
         obj9.setTitle("ECE331 - Digital System Design");
         obj9.setLat("38.833185");
         obj9.setLongi("-77.307531");
         obj9.setAlarmID(9);
         ece.add(obj9);

         SubjectClass obj10 = new SubjectClass();
         obj10.setEndTime("17:45");
         obj10.setInstructor("Huang");
         obj10.setStarttime("16:30");
         obj10.setDays("T-R");
         obj10.setLocation("Enterprise Hall 178");
         obj10.setTitle("ECE415 - Power System Analysis");
         obj10.setLat("38.829032");
         obj10.setLongi("-77.306368");
         obj10.setAlarmID(10);
         ece.add(obj10);

         SubjectClass obj11 = new SubjectClass();
         obj11.setEndTime("10:15");
         obj11.setInstructor("Zhang");
         obj11.setStarttime("9:00");
         obj11.setDays("M-W");
         obj11.setLocation("East 201");
         obj11.setTitle("ECE421 - Clsscl Systm and Cntrl Thry");
         obj11.setLat("38.832995");
         obj11.setLongi("-77.308255");
         obj11.setAlarmID(11);
         ece.add(obj11);

         SubjectClass obj12 = new SubjectClass();
         obj12.setEndTime("13:15");
         obj12.setInstructor("Zhang");
         obj12.setStarttime("12:00");
         obj12.setDays("M-W");
         obj12.setLocation("Innovation 206");
         obj12.setTitle("ECE422 - Digital Control Systems");
         obj12.setLat("38.828630");
         obj12.setLongi("-77.307440");
         obj12.setAlarmID(12);
         ece.add(obj12);

         SubjectClass obj13 = new SubjectClass();
         obj13.setEndTime("11:45");
         obj13.setInstructor("Mark");
         obj13.setStarttime("10:30");
         obj13.setDays("T-R");
         obj13.setLocation("Art and Design 2003");
         obj13.setTitle("ECE465 - Computer Networking Protocols");
         obj13.setLat("38.828135");
         obj13.setLongi("-77.306315");
         obj13.setAlarmID(13);
         ece.add(obj13);


         //MATH

         SubjectClass obj14 = new SubjectClass();
         obj14.setEndTime("12:20");
         obj14.setInstructor("Sachs");
         obj14.setStarttime("10:30");
         obj14.setDays("T-R");
         obj14.setLocation("Robinson B104");
         obj14.setTitle("MATH113 - Analytic Geometry/Calculus I Lecture");
         obj14.setLat("38.8307");
         obj14.setLongi("-77.3077");
         obj14.setAlarmID(14);
         math.add(obj14);

         SubjectClass obj15 = new SubjectClass();
         obj15.setEndTime("09:20");
         obj15.setInstructor("Green");
         obj15.setStarttime("08:30");
         obj15.setDays("F");
         obj15.setLocation("Peterson 1105");
         obj15.setTitle("MATH113 - Analytic Geometry/Calculus I Recitation");
         obj15.setLat("38.8341");
         obj15.setLongi("-77.3089");
         obj15.setAlarmID(15);
         math.add(obj15);

         SubjectClass obj16 = new SubjectClass();
         obj16.setEndTime("18:20");
         obj16.setInstructor("Love");
         obj16.setStarttime("16:30");
         obj16.setDays("M-W");
         obj16.setLocation("David King Hall 1006");
         obj16.setTitle("MATH114 - Analytic Geometry/Calculus II Lecture");
         obj16.setLat("38.8306");
         obj16.setLongi("-77.30647");
         obj16.setAlarmID(16);
         math.add(obj16);

         SubjectClass obj17 = new SubjectClass();
         obj17.setEndTime("11:20");
         obj17.setInstructor("Stanley");
         obj17.setStarttime("10:30");
         obj17.setDays("F");
         obj17.setLocation("Engineering 1103");
         obj17.setTitle("MATH114 - Analytic Geometry/Calculus II Recitation");
         obj17.setLat("38.82788");
         obj17.setLongi("-77.3048");
         obj17.setAlarmID(17);
         math.add(obj17);

         SubjectClass obj18 = new SubjectClass();
         obj18.setEndTime("10:15");
         obj18.setInstructor("O’Brien");
         obj18.setStarttime("09:00");
         obj18.setDays("T-R");
         obj18.setLocation("Enterprise Hall 80");
         obj18.setTitle("MATH125 - Discrete Mathematics I");
         obj18.setLat("38.8289");
         obj18.setLongi("-77.30609");
         obj18.setAlarmID(18);
         math.add(obj18);

         SubjectClass obj19 = new SubjectClass();
         obj19.setEndTime("11:45");
         obj19.setInstructor("Holzer");
         obj19.setStarttime("10:30");
         obj19.setDays("M-W");
         obj19.setLocation("David King Hall 1006");
         obj19.setTitle("MATH203 - Linear Algebra");
         obj19.setLat("38.8306");
         obj19.setLongi("-77.30647");
         obj19.setAlarmID(19);
         math.add(obj19);

         SubjectClass obj20 = new SubjectClass();
         obj20.setEndTime("14:45");
         obj20.setInstructor("Lukyanenko");
         obj20.setStarttime("13:30");
         obj20.setDays("M-W");
         obj20.setLocation("Planetary Hall 131");
         obj20.setTitle("MATH213 - Analytic Geom/Calculus III Lecture");
         obj20.setLat("38.8299");
         obj20.setLongi("-77.3081");
         obj20.setAlarmID(20);
         math.add(obj20);

         SubjectClass obj21 = new SubjectClass();
         obj21.setEndTime("11:20");
         obj21.setInstructor("Mahzari");
         obj21.setStarttime("10:30");
         obj21.setDays("R");
         obj21.setLocation("Aquia 213");
         obj21.setTitle("MATH213 - Analytic Geom/Calculus III Recitation");
         obj21.setLat("38.8321");
         obj21.setLongi("-77.3092");
         obj21.setAlarmID(21);
         math.add(obj21);


         //SYS

         SubjectClass obj22 = new SubjectClass();
         obj22.setEndTime("17:45");
         obj22.setInstructor("Rothwell");
         obj22.setStarttime("16:30");
         obj22.setDays("T-R");
         obj22.setLocation("Peterson Hall 1113");
         obj22.setTitle("SYST101 - Understanding Syst Engr");
         obj22.setLat("38.834383");
         obj22.setLongi("-77.309063");
         obj22.setAlarmID(22);
         syst.add(obj22);

         SubjectClass obj23 = new SubjectClass();
         obj23.setEndTime("13:15");
         obj23.setInstructor("Shortle");
         obj23.setStarttime("12:00");
         obj23.setDays("M-W");
         obj23.setLocation("Planetary 124");
         obj23.setTitle("SYST320 - Dynamical Systems II");
         obj23.setLat("38.829898");
         obj23.setLongi("-77.306281");
         obj23.setAlarmID(23);
         syst.add(obj23);

         SubjectClass obj24 = new SubjectClass();
         obj24.setEndTime("16:10");
         obj24.setInstructor("Morris ");
         obj24.setStarttime("13:30");
         obj24.setDays("F");
         obj24.setLocation("Engineering 1110");
         obj24.setTitle("SYST420 - Network Analysis");
         obj24.setLat("38.827759");
         obj24.setLongi("-77.305059");
         obj24.setAlarmID(24);
         syst.add(obj24);

         SubjectClass obj25 = new SubjectClass();
         obj25.setEndTime("19:10");
         obj25.setInstructor("Lance Sherry");
         obj25.setStarttime("16:30");
         obj25.setDays("M");
         obj25.setLocation("Buchanan Hall D003");
         obj25.setTitle("SYST 460 - Intro to Air Traffic Control ");
         obj25.setLat("38.828809");
         obj25.setLongi("-77.308554");
         obj25.setAlarmID(25);
         syst.add(obj25);

         SubjectClass obj26 = new SubjectClass();
         obj26.setEndTime("22:00");
         obj26.setInstructor("Kuo-Chu Chang");
         obj26.setStarttime("19:20");
         obj26.setDays("M");
         obj26.setLocation("Innovation Hall 134");
         obj26.setTitle("SYST438 - Ana Financial Eng/Econometrics");
         obj26.setLat("38.828630");
         obj26.setLongi("-77.307440");
         obj26.setAlarmID(26);
         syst.add(obj26);


         //SWE
         SubjectClass obj27 = new SubjectClass();
         obj27.setEndTime("13:15");
         obj27.setInstructor("Paul Ammann");
         obj27.setStarttime("12:00");
         obj27.setDays("F");
         obj27.setLocation("Innovation building 215G");
         obj27.setTitle("SWE301 -Internship Preparation");
         obj27.setLat("38.828630");
         obj27.setLongi("-77.307440");
         obj27.setAlarmID(27);
         swe.add(obj27);

         SubjectClass obj28 = new SubjectClass();
         obj28.setEndTime("11:45");
         obj28.setInstructor("Jeff Offutt");
         obj28.setStarttime("10:00");
         obj28.setDays("T-R");
         obj28.setLocation("MERTEN 1200");
         obj28.setTitle("SWE437 - Software Testing/Maintenance");
         obj28.setLat("38.835077");
         obj28.setLongi("-77.307923");
         obj28.setAlarmID(28);
         swe.add(obj28);

         SubjectClass obj29 = new SubjectClass();
         obj29.setEndTime("14:40");
         obj29.setInstructor("LaToza");
         obj29.setStarttime("13:30");
         obj29.setDays("M-W");
         obj29.setLocation("MERTEN 1203");
         obj29.setTitle("SWE432 - Web App Development");
         obj29.setLat("8.835077");
         obj29.setLongi("-77.307923");
         obj29.setAlarmID(29);
         swe.add(obj29);

         SubjectClass obj30 = new SubjectClass();
         obj30.setEndTime("19:10");
         obj30.setInstructor("Reep");
         obj30.setStarttime("16:30");
         obj30.setDays("M-W");
         obj30.setLocation(" Innovation building 208");
         obj30.setTitle("SWE443 - Software Architectures");
         obj30.setLat("38.828630");
         obj30.setLongi("-77.307440");
         obj30.setAlarmID(30);
         swe.add(obj30);

         SubjectClass obj31 = new SubjectClass();
         obj31.setEndTime("22:00");
         obj31.setInstructor("Kowalski");
         obj31.setStarttime("19:20");
         obj31.setDays("R");
         obj31.setLocation("Innovation building 134");
         obj31.setTitle("SWE510 - Object-Oriented Programming java");
         obj31.setLat("38.828630");
         obj31.setLongi("-77.307440");
         obj31.setAlarmID(31);
         swe.add(obj31);

     }
}
