package com.example.myapplication;
/*
Neel patel
    CS 321-006 Team 4
 */

public class SubjectClass {
    String title;
    String starttime;
    String endTime;
    String Location;
    String instructor;
    String days;
    String lat;
    String id;

    public int getAlarmID() { return alarmID; }

    public void setAlarmID(int alarmID) { this.alarmID = alarmID; }

    int alarmID; // For easy control of multiple Alarms.
    // Format: obj# * 100 -> obj = 000, obj1 = 100



    public String getDays(){ return days;}

    public void setDays(String days){ this.days = days;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    String longi;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
