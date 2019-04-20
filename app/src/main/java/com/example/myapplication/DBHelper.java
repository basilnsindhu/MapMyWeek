package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "SQLite.db";
    private static final int DATABASE_VERSION = 3;
    public static final String SUBJECT_TABLE_NAME = "subject";
    public static final String SUBJECT_COLUMN_ID = "_id";
    public static final String SUBJECT_COLUMN_TITLE = "title";
    public static final String SUBJECT_COLUMN_INST = "inst";
    public static final String SUBJECT_COLUMN_LOC = "loc";
    public static final String SUBJECT_COLUMN_LAT = "lat";
    public static final String SUBJECT_COLUMN_LONGI = "long";
    public static final String SUBJECT_COLUMN_DAYS = "days";
    public static final String SUBJECT_COLUMN_STARTTIME = "starttime";
    public static final String SUBJECT_COLUMN_ENDTIME = "endtime";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + SUBJECT_TABLE_NAME + "(" +
                SUBJECT_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                SUBJECT_COLUMN_TITLE + " TEXT, " +
                SUBJECT_COLUMN_INST + " TEXT, " +
                SUBJECT_COLUMN_STARTTIME + " TEXT, " +
                SUBJECT_COLUMN_ENDTIME + " TEXT, " +
                SUBJECT_COLUMN_DAYS + " TEXT, " +
                SUBJECT_COLUMN_LAT + " TEXT, " +
                SUBJECT_COLUMN_LONGI + " TEXT, " +
                SUBJECT_COLUMN_LOC + " TEXT)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SUBJECT_TABLE_NAME);
        onCreate(db);
    }
    public boolean insertSUBJECT(String title, String inst, String starttime, String endtime, String days, String lat, String longi , String loc) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.i("title",title);
        contentValues.put(SUBJECT_COLUMN_TITLE, title);
        contentValues.put(SUBJECT_COLUMN_INST, inst);
        contentValues.put(SUBJECT_COLUMN_STARTTIME, starttime);
        contentValues.put(SUBJECT_COLUMN_ENDTIME, endtime);
        contentValues.put(SUBJECT_COLUMN_DAYS, days);
        contentValues.put(SUBJECT_COLUMN_LAT, lat);
        contentValues.put(SUBJECT_COLUMN_LONGI, longi);
        contentValues.put(SUBJECT_COLUMN_LOC, loc);

        db.insert(SUBJECT_TABLE_NAME, null, contentValues);
        return true;
    }
    public boolean updateSUBJECT(int id, String title, String inst, String starttime, String endtime, String days, String lat, String longi , String loc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBJECT_COLUMN_TITLE, title);
        contentValues.put(SUBJECT_COLUMN_INST, inst);
        contentValues.put(SUBJECT_COLUMN_STARTTIME, starttime);
        contentValues.put(SUBJECT_COLUMN_ENDTIME, endtime);
        contentValues.put(SUBJECT_COLUMN_DAYS, days);
        contentValues.put(SUBJECT_COLUMN_LAT, lat);
        contentValues.put(SUBJECT_COLUMN_LONGI, longi);
        contentValues.put(SUBJECT_COLUMN_LOC, loc);
        db.update(SUBJECT_TABLE_NAME, contentValues, SUBJECT_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Cursor getSUBJECT(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + SUBJECT_TABLE_NAME + " WHERE " +
                SUBJECT_COLUMN_ID + "=?", new String[] { Integer.toString(id) } );
        return res;
    }
    public Cursor getAllSUBJECTs() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + SUBJECT_TABLE_NAME, null );
        return res;
    }

    public Integer deleteSUBJECT(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(SUBJECT_TABLE_NAME,
                SUBJECT_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }
}


//public class DBHelper {
//    SharedPreferences prefs;
//    SharedPreferences prefs_subject;
//    SharedPreferences.Editor editor;
//    SharedPreferences.Editor editor_subject;
//    Context ctx;
//
//    public DBHelper(Context ctx) {
//        this.ctx = ctx;
//        prefs = ctx.getSharedPreferences("myapp", Context.MODE_PRIVATE);
//        prefs_subject = ctx.getSharedPreferences("a", Context.MODE_PRIVATE);
//        editor = prefs.edit();
//        editor_subject = prefs_subject.edit();
//    }
//
//    public void addSubject(String Id,String starttime,String endtime,String title,String location,String inst,String lat,String longi ) {
//        editor.putString("Id",Id);
//        editor.putString("starttime",starttime);
//        editor.putString("endtime",endtime);
//        editor.putString("title",title);
//        editor.putString("location",location);
//        editor.putString("inst",inst);
//        editor.putString("lat",lat);
//        editor.putString("longi",longi);
//        editor.commit();
//    }
//    public String getId() {
//
//        return prefs.getString("Id","false");
//    }
//    public String getstarttime() {
//
//        return prefs.getString("starttime","NO time");
//    }
//    public String getendtime() {
//
//        return prefs.getString("endtime","NO time");
//    }
//    public String gettitle() {
//
//        return prefs.getString("title","NO Name");
//    }
//    public String getlocation() {
//
//        return prefs.getString("location","NO loc");
//    }
//    public String getinst() {
//
//        return prefs.getString("inst","NO inst");
//    }
//    public String getlat() {
//
//        return prefs.getString("lat","NOT lat");
//    }
//    public String getlongi() {
//
//        return prefs.getString("longi","NOT long");
//    }
////    public void logout() {
////        editor.clear();
////        editor.commit();
////
////        editor_subject.clear();
////        editor_subject.commit();
////    }
//}
