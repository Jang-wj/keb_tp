package com.example.teamproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MyDBOpenHelper mydb;
    SQLiteDatabase mdb;

    Button btTriGame, btDB;
    Button bt1, bt3, bt4, bt5, bt6;


    final int RQCodeBlankGame = 1;
    final int RQCodeTriGame = 2;
    final int RQCode3= 3;
    final int RQCode4 = 4;
    final int RQCode5 = 5;
    final int RQCode6 = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new MyDBOpenHelper(this, "mydb.db", null, 1);
        mdb = mydb.getWritableDatabase();

        bt1 = findViewById(R.id.bt1);
        btTriGame = findViewById(R.id.bt2);
        btDB = findViewById(R.id.btdb);

        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt6 = findViewById(R.id.bt6);

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, MemoryGame_Chae.class);
                startActivityForResult(i, RQCode3);
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, CardGame_Kang.class);
                startActivityForResult(i, RQCode4);
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, SameNumberGame_Hojin.class);
                startActivityForResult(i, RQCode4);
            }
        });

        btTriGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(MainActivity.this, TriangleGame.class);
                startActivityForResult(i, RQCodeTriGame);
            }
        });

        btDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent();
                i3.setClass(MainActivity.this, ResultDB.class);
                startActivity(i3);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int res = data.getIntExtra("result", 0);

        if (requestCode == RQCodeBlankGame && resultCode == RESULT_OK) {
            mdb.execSQL("INSERT INTO BLANKGAME VALUES (null, '" + res + "')");
        }
        else if (requestCode == RQCodeTriGame && resultCode == RESULT_OK){
            mdb.execSQL("INSERT INTO TRIANGLEGAME VALUES (null, '" + res + "')");
        }
    }

     // bt5 김도균
    public void startGame(View view) {
        setContentView(new AnimalGame_DoGyun(this));
    }
}


class MyDBOpenHelper extends SQLiteOpenHelper {
    public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        // create table
        db.execSQL("CREATE TABLE BLANKGAME (_id INTEGER PRIMARY KEY AUTOINCREMENT, result INTEGER);");
        db.execSQL("CREATE TABLE TRIANGLEGAME (_id INTEGER PRIMARY KEY AUTOINCREMENT, result INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}