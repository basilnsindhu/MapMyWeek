package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
/*
Neel patel
    CS 321-006 Team 4
 */

public class NavActivity extends AppCompatActivity {
    ListView listView;
    DBHelper dbHelper;
    ArrayList<SubjectClass> list;
    SubjectsAdapter adapter;
    double latitude;
    double logitude;
    private boolean mLocation_permissiongrannted = false;
    private static final int locationrequest_code = 1234;
    private boolean mCall_permissiongrannted = false;
    GoogleMap mMap;
    android.location.Location current_location;
    LatLng latLng2;
    LocationManager locationManager;
    private static final String COARSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;

    String lat, longt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.nav_list_view);
        dbHelper = new DBHelper(getApplicationContext());
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (islocationenable()) {
            //Toast.makeText(getApplicationContext(),"In If",Toast.LENGTH_LONG).show();
            getpermission();
        }
        Cursor cursor = dbHelper.getAllSUBJECTs();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                SubjectClass obj = new SubjectClass();
//                Toast.makeText(getApplicationContext(),cursor.getString(cursor.getColumnIndex("_id")),Toast.LENGTH_SHORT).show();
                obj.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                obj.setLongi(cursor.getString(cursor.getColumnIndex("long")));
                obj.setLat(cursor.getString(cursor.getColumnIndex("lat")));
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
                //String uri = "http://maps.google.com/maps?saddr=" + lat + "," + longt + "&daddr=" + list.get(position).getLat() + "," + list.get(position).getLongi()+ "&travelmode=walking";
                String uri = "http://maps.google.com/maps?&daddr=" + list.get(position).getLat() + "," + list.get(position).getLongi()+ "&mode=w";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
    }

    private boolean islocationenable() {

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {

            android.app.AlertDialog.Builder myaleart = new android.app.AlertDialog.Builder(this);
            myaleart.setMessage("Gps network not enabled").setPositiveButton("Open Location Setting", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    NavActivity.this.startActivity(myIntent);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            myaleart.show();
            return false;

        }
        return true;
    }

    private void getpermission() {
        //Toast.makeText(getApplicationContext(),"In Permission",Toast.LENGTH_LONG).show();
        String[] permission = {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if ((ContextCompat.checkSelfPermission(getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                mLocation_permissiongrannted = true;
//                initmap();
                if (islocationenable()) {
//                    get_device_location_login();
                    try {
//                        final Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        current_location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);

                    }


                    try {
                        //Toast.makeText(getApplicationContext(),"In Try", Toast.LENGTH_LONG).show();
                        latitude = current_location.getLatitude();
                        logitude = current_location.getLongitude();
                        lat = Double.toString(latitude);
                        longt = Double.toString(logitude);
                        Log.i("Lat",lat);
                        Log.i("Longt",longt);
                    } catch (Exception e) {

                    }
                }


            } else {
                ActivityCompat.requestPermissions(this, permission, locationrequest_code);
            }
        } else {
            ActivityCompat.requestPermissions(this, permission, locationrequest_code);
        }
    }
    @SuppressLint("MissingPermission")
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocation_permissiongrannted = false;
        switch (requestCode) {
            case locationrequest_code: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocation_permissiongrannted = false;
                            return;
                        }
                    }
                    mLocation_permissiongrannted = true;
//                    initmap();
                    current_location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

//                        current_location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


                }
            }
        }
    }

}
