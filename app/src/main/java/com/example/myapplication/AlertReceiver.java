package com.example.myapplication;
/*
    Jared Bannan
    CS 321-006 Team 4
 */


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {

    //  TODO: Make getChannelXNotification work with courses

    @Override
    public void onReceive(Context context, Intent intent) {
        // called when alarm is fired
        String[] data = intent.getStringArrayExtra("notificationData");// time, title, location
        int AlarmID = intent.getIntExtra("AlarmID", 0);
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb;
        if (data != null) {
           nb = notificationHelper.getAlarmChannelNotification(data[1], "Class in " + data[2] + " at " + data[0]);
        } else {
            nb = notificationHelper.getAlarmChannelNotification("title", "message");
        }
        notificationHelper.getManager().notify(AlarmID, nb.build());
    }
}
