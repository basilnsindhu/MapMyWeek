package com.example.myapplication;
/*
    Jared Bannan
    CS 321-006 Team 4
 */

/*
    TODO:
        - Generalize Channel creation
        - Implement Channel creation via Courses (including sendOnChannel(title, message))
        - Change NotificationHelper() to accommodate changes
        - Change notification builder to have relevant information
 */

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

public class NotificationHelper extends ContextWrapper {

    // Class Alarm Channel
    public static final String alarm_channel_ID = "CourseAlarm";
    public static final String alarm_channel_Name = "Course Alarm";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }

    }


    @TargetApi(Build.VERSION_CODES.O)
    public void createChannels() {
        //Upcoming Class Alarm
        NotificationChannel alarm_channel = new NotificationChannel(alarm_channel_ID, alarm_channel_Name, NotificationManager.IMPORTANCE_HIGH);
        alarm_channel.setDescription("Alarm for upcoming class");
        mManager = getManager();
        mManager.createNotificationChannel(alarm_channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }


    /* Need in class scheduler:
        private NotificationHelper mNotificationHelper;
        public void sendOnChannelX(title, message){
            NotificationCompat.Builder nb = mNotificationHelper.getAlarmChannelNotification(title, message)
            mNotificationHelper.getManager().notify(1, nb.build());
        }

     */

    // Maybe add content and OnClick navigation
    public NotificationCompat.Builder getAlarmChannelNotification(String title, String text) {
        Intent navIntent = new Intent(this, NavActivity.class);
        navIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, navIntent, 0);

        return new NotificationCompat.Builder(getApplicationContext(), alarm_channel_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark) // Need to add in a notification icon to drawables
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

    }

}
