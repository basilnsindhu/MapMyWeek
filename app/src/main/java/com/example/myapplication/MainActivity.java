package com.example.myapplication;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

   // private NotificationHelper mNotificationHelper;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_mpage);
        Button schedule = findViewById(R.id.btn_schedule);
        Button manage = findViewById(R.id.btn_addrem);
        Button gotoclass = findViewById(R.id.btn_nav);
       // mNotificationHelper = new NotificationHelper(this);

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BasicActivity.class);
                startActivity(intent);
            }
        });

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddRemoveActivity.class);
                startActivity(intent);
            }
        });

        gotoclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NavActivity.class);
                startActivity(intent);
//                String uri = "http://maps.google.com/maps?saddr=" + 30.8138 + "," + 73.4534 + "&daddr=" + 28.4212 + "," + 70.2989;
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                startActivity(intent);
            }
        });

        /*gotoclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_page.this, directions.class);
                startActivity(intent);
            }*/
    };
    private void addNotification() {
        String uri = "http://maps.google.com/maps?saddr=" + 30.8138 + "," + 73.4534 + "&daddr=" + 28.4212 + "," + 70.2989;
        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//        Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, 0);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification Upcoming Class")
                        .setContentText("Tap to Navigate")
                        .setContentIntent(pendingIntent);

        // Gets an instance of the NotificationManager service//
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }


}
