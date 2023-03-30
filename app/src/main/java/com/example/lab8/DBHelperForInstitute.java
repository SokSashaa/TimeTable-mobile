package com.example.lab8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperForInstitute extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=2;
    public static final String DATABASE_NAME = "institute";
    public static final String TABLE_CONTACT="institutes";
    public static  final String KEY_ID="_id";
    public static final String KEY_NAME="name";


    public DBHelperForInstitute(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACT + " (" + KEY_ID + " integer primary key,"+KEY_NAME+ " text" + ") ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists " + TABLE_CONTACT);
        onCreate(db);
    }
}
