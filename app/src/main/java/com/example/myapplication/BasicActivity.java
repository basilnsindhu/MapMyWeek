package com.example.myapplication;

import android.database.Cursor;
import android.graphics.Color;

import com.alamkanak.weekview.WeekViewEvent;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/*
    Basil Sindhu
    CS 321-006 Team 4
 */
public class BasicActivity extends BaseActivity {




    /*void addEvent(int newYear, int newMonth){
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 2);
        endTime.set(Calendar.MONTH, newMonth - 1);
        WeekViewEvent event = new WeekViewEvent(1, "Event 1", startTime, endTime);
        onMonthChange(newYear,newMonth);
        //event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event); }
*/

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        DBHelper db = new DBHelper(getApplicationContext());
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        Cursor cursor = db.getAllSUBJECTs();
        ArrayList<SubjectClass> list = new ArrayList<>();
        if(cursor.moveToFirst()){
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
        String colors[] = {"event_color_01", "event_color_2"};


        for(int i=0;i<list.size();i++){
            Calendar startTime;
            SubjectClass temp = list.get(i);
            int j;
            int day1 = 0;
            int day2 = 0;
            if(temp.getDays().length()>1){
                day1 = getDay1(temp.getDays());
                day2 = getDay2(temp.getDays());
                j=0;}
            else{
                day1 = getDay1(temp.getDays());
                j=1;}
            while(j<2) {
                startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, getHour(temp.getStarttime()));
                startTime.set(Calendar.MINUTE, getMin(temp.getStarttime()));
                startTime.set(Calendar.DAY_OF_MONTH,day1);
                startTime.set(Calendar.MONTH, newMonth - 1);
                startTime.set(Calendar.YEAR, newYear);
                Calendar endTime = (Calendar) startTime.clone();
                //endTime.add(Calendar.HOUR, getHour(temp.getEndTime()));
                endTime.set(Calendar.HOUR_OF_DAY, getHour(temp.getEndTime()));
                endTime.set(Calendar.MINUTE, getMin(temp.getEndTime()));
                //endTime.set(Calendar.DAY_OF_WEEK, day1);
                endTime.set(Calendar.MONTH, newMonth - 1);
                WeekViewEvent event = new WeekViewEvent(1, temp.getTitle(), startTime, endTime);
                j++;
                day1 = day2;
                //event.setColor(getResources().getColor(R.color.event_color_03));
                event.setColor(getRandomColor());
                events.add(event);
            }
        }



        /*Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 2);
        endTime.set(Calendar.MONTH, newMonth - 1);
        WeekViewEvent event = new WeekViewEvent(1, "Event 1", startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);*/
        return events;
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public int getDay1(String s){
        int day = 0;
        //Calendar today = Calendar.getInstance();
        LocalDate ld = LocalDate.now();

        String[] split = s.split("-");
        if(split[0].equals("M")){ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));}
        else if(split[0].equals("T")){ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));}
        else if(split[0].equals("W")){ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));}
        else if(split[0].equals("R")){ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));}
        else if(split[0].equals("F")){ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));}
        day = ld.getDayOfMonth();
        return day;

    }

    public int getDay2(String s){
        int day = 0;
        LocalDate ld = LocalDate.now();

        String[] split = s.split("-");
        if(split[1].equals("M")){ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));}
        else if(split[1].equals("T")){ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));}
        else if(split[1].equals("W")){ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));}
        else if(split[1].equals("R")){ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));}
        else if(split[1].equals("F")){ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));}
        day = ld.getDayOfMonth();
        return day;

    }

    public int getHour(String s){
        int hour = 0;
        String[] split = s.split(":");

        hour = Integer.parseInt(split[0]);
        return hour;
    }

    public int getMin(String s){
        int min = 0;
        String[] split = s.split(":");
        //String temp = split[1];
       // if(temp.contains("am")){temp=temp.replace("am","");}
       // else if(temp.contains("pm")){temp=temp.replace("pm","");}
        min = Integer.parseInt(split[1]);
        return min;
    }


    // Populate the week view with some events.
        //List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();


        /*Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 2);
        endTime.set(Calendar.MONTH, newMonth - 1);
        WeekViewEvent event = new WeekViewEvent(1, "Event 1", startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

       /* startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 4);
        endTime.set(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 4);
        startTime.set(Calendar.MINUTE, 20);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 5);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        startTime.add(Calendar.DATE, 1);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_04));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 1);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        //AllDay event
        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 23);
        event = new WeekViewEvent(7, getEventTitle(startTime), null, startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_04));
        events.add(event);
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 2);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.DAY_OF_MONTH, 10);
        endTime.set(Calendar.HOUR_OF_DAY, 23);
        event = new WeekViewEvent(8, getEventTitle(startTime), null, startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        // All day event until 00:00 next day
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 10);
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.DAY_OF_MONTH, 11);
        event = new WeekViewEvent(8, getEventTitle(startTime), null, startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);*/


}
