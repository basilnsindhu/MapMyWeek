package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class DeleteActivity extends AppCompatActivity {
    ListView listView;
    DBHelper dbHelper;
    ArrayList<SubjectClass> list = new ArrayList<>();
    SubjectsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        listView = (ListView) findViewById(R.id.delete_list_view);
        dbHelper = new DBHelper(getApplicationContext());

        Cursor cursor = dbHelper.getAllSUBJECTs();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                SubjectClass obj = new SubjectClass();
//                Toast.makeText(getApplicationContext(),cursor.getString(cursor.getColumnIndex("_id")),Toast.LENGTH_SHORT).show();
                obj.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                obj.setLongi(cursor.getString(cursor.getColumnIndex("long")));
                obj.setLat(cursor.getString(cursor.getColumnIndex("lat")));
                obj.setDays(cursor.getString(cursor.getColumnIndex("days")));
                obj.setLocation(cursor.getString(cursor.getColumnIndex("loc")));
                obj.setInstructor(cursor.getString(cursor.getColumnIndex("inst")));
                obj.setEndTime(cursor.getString(cursor.getColumnIndex("endtime")));
                obj.setStarttime(cursor.getString(cursor.getColumnIndex("starttime")));
                obj.setId(cursor.getString(cursor.getColumnIndex("_id")));

                list.add(obj);
                cursor.moveToNext();
            }
        }

        adapter = new SubjectsAdapter(getApplicationContext(),list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cancelAlarm(list.get(position).getAlarmID(), list.get(position).getDays());
                dbHelper.deleteSUBJECT(Integer.valueOf(list.get(position).getId()));
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),AddRemoveActivity.class);
                startActivity(intent);
                finish();
            }
    });
    }

    private void cancelAlarm(int alarmID, String _days) {
        String[] dayArr = _days.split("-");
        int[] numDays = {1, 1, 1};
        int ID = alarmID;
        for (int i = 0; i < dayArr.length; i++) {
            if (dayArr[i].charAt(0) == 'M') {
                ID += 2;
                numDays[i] = Calendar.MONDAY;
            } else if (dayArr[i].charAt(0) == 'T') {
                ID += 3;
                numDays[i] = Calendar.TUESDAY;
            } else if (dayArr[i].charAt(0) == 'W') {
                ID += 4;
                numDays[i] = Calendar.WEDNESDAY;
            } else if (dayArr[i].charAt(0) == 'R') {
                ID += 5;
                numDays[i] = Calendar.THURSDAY;
            } else if (dayArr[i].charAt(0) == 'F') {
                ID += 6;
                numDays[i] = Calendar.FRIDAY;
            } else {
                ID += 7;
                numDays[i] = Calendar.SATURDAY;
            }
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, ID, intent, 0);

            alarmManager.cancel(pendingIntent);
        }
/*
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, ID, intent, 0);

        alarmManager.cancel(pendingIntent);

 */
    }
}
