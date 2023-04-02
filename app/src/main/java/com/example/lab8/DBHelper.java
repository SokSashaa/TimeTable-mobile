package com.example.lab8;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "contract";
    public static final String TABLE_CONTACT="contacts";

    public static  final String KEY_ID="_id";
    public static final String KEY_INSTITUTE="inst";
    public static final String KEY_SPEC="spec";
    public static final String KEY_GROUP="groupe";
    public static final String KEY_TYPE_WEEK="type_week";
    public static final String KEY_DAY_OF_WEEK = "day_of_week";
    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_TEACHER = "teacher";
    public static final String KEY_PLACE = "place";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_TYPE_SUBJECT = "type_subject";
    public static final String KEY_TIME = "time";



    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACT + " ("
                + KEY_ID + " integer primary key,"
                +KEY_INSTITUTE+ " text,"
                + KEY_SPEC + " text,"
                + KEY_GROUP + " text,"
                + KEY_TYPE_WEEK + " text,"
                + KEY_DAY_OF_WEEK + " text,"
                + KEY_TIME + " text,"
                + KEY_SUBJECT + " text,"
                + KEY_PLACE + " text,"
                + KEY_TYPE_SUBJECT + " text,"
                + KEY_TEACHER + " text,"
                + KEY_COMMENT + " text"
                + ") ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists " + TABLE_CONTACT);
        onCreate(db);
    }
}
