package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ResultDB extends AppCompatActivity {
    MyDBOpenHelper mydb;
    SQLiteDatabase mdb;
    ListView lv1, lv2;
    Cursor cursor1, cursor2;
    SimpleCursorAdapter ca1, ca2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_db);

        lv1 = findViewById(R.id.list1);
        lv2 = findViewById(R.id.list2);

        String[] strCol = {"result"};

        mydb = new MyDBOpenHelper(this, "mydb.db", null, 1);
        mdb = mydb.getReadableDatabase();

        cursor1 = mdb.rawQuery("select * from BLANKGAME;", null);
        ca1 = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, cursor1, strCol,
                new int[]{android.R.id.text1});
        lv1.setAdapter(ca1);


        cursor2 = mdb.rawQuery("select * from TRIANGLEGAME;", null);
        ca2 = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, cursor2, strCol,
                new int[]{android.R.id.text1});
        lv2.setAdapter(ca2);
    }

    final int Exit = Menu.FIRST;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, Exit, Menu.NONE, "Exit");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == Exit) {
            Intent ir = getIntent();
            ir.setClass(ResultDB.this, MainActivity.class);
            startActivity(ir);
        }


        return super.onOptionsItemSelected(item);
    }
}