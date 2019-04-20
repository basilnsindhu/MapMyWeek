package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

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
                dbHelper.deleteSUBJECT(Integer.valueOf(list.get(position).getId()));
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),AddRemoveActivity.class);
                startActivity(intent);
                finish();
            }
    });
    }
}
