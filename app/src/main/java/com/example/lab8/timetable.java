package com.example.lab8;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class timetable extends AppCompatActivity {
    SQLiteDatabase dbtimetable;
    String inst;
    String spec;
    String group;
    String type_week;
    String day;
    Cursor cursor;
    TextView[] array;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);
        try{
            DBHelper dbHelper = new DBHelper(this);
            dbtimetable = dbHelper.getWritableDatabase();

            //  set();
            inst = getIntent().getStringExtra("inst");
            spec = getIntent().getStringExtra("spec");
            group = getIntent().getStringExtra("groupe");
            type_week = getIntent().getStringExtra("type_week");
            day = getIntent().getStringExtra("day");

            array = new TextView[] {
                    (TextView)findViewById(R.id.textView16),
                    (TextView)findViewById(R.id.textView17),
                    (TextView)findViewById(R.id.textView18),
                    (TextView)findViewById(R.id.textView19),
                    (TextView)findViewById(R.id.textView20),
                    (TextView)findViewById(R.id.textView22),
                    (TextView)findViewById(R.id.textView21),
            };
            setInformationForList();
        }
        catch (Exception e)
        {
          Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);
        }


    }

    private void setInformationForList()
    {
        try{
            String selection = "inst = ? AND spec = ? AND groupe = ? AND type_week = ? AND day_of_week = ?";
            cursor = dbtimetable.query(DBHelper.TABLE_CONTACT,null,selection,new String[] {inst,spec,group,type_week,day},null,null,null);

            if(cursor.moveToFirst()){
                int numberIndex = cursor.getColumnIndex(DBHelper.KEY_NUMBER);
                int subjectIndex = cursor.getColumnIndex(DBHelper.KEY_SUBJECT);
                int teacherIndex = cursor.getColumnIndex(DBHelper.KEY_TEACHER);
                int placeIndex = cursor.getColumnIndex(DBHelper.KEY_PLACE);
                int typeSubIndex = cursor.getColumnIndex(DBHelper.KEY_TYPE_SUBJECT);
                int commentIndex = cursor.getColumnIndex(DBHelper.KEY_COMMENT);
                do{
                    Integer numberInd = cursor.getInt(numberIndex);
                    String sub = cursor.getString(subjectIndex);
                    String pl = cursor.getString(placeIndex);
                    String tp = cursor.getString(typeSubIndex);
                    String teach = cursor.getString(teacherIndex);
                    String com = cursor.getString(commentIndex);
                    String s = array[numberInd-1].getText().toString();
                    if(s=="")
                    {
                        array[numberInd-1].setText(sub + "\n" + pl + "("+ tp + ") " + teach + "\n" + com);
                    }
                    else {
                        array[numberInd-1].setTextSize(10);
                        array[numberInd-1].setText(s + "\n" + "-----" + "\n" + sub + "\n" + pl + "("+ tp + ") " + teach + "\n" + com );
                    }

                }
                while(cursor.moveToNext());
            }
            cursor.close();
        }
        catch (Exception e){ Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }

    public void set()
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_INSTITUTE,"ИАСТ");
        contentValues.put(DBHelper.KEY_SPEC,"ИСТ");
        contentValues.put(DBHelper.KEY_GROUP,"21-ИСБО-1");
        contentValues.put(DBHelper.KEY_TYPE_WEEK,"Над чертой");
        contentValues.put(DBHelper.KEY_DAY_OF_WEEK,"Понедельник");
        contentValues.put(DBHelper.KEY_SUBJECT,"Англ.яз");
        contentValues.put(DBHelper.KEY_NUMBER,2);
        contentValues.put(DBHelper.KEY_PLACE,"Е-308");
        contentValues.put(DBHelper.KEY_TYPE_SUBJECT,"Практика");
        contentValues.put(DBHelper.KEY_TEACHER,"Глазова Е.Ю.");
        contentValues.put(DBHelper.KEY_COMMENT,"только 21-ИСБО-1а");
        dbtimetable.insert(DBHelper.TABLE_CONTACT,null,contentValues);
    }
}
