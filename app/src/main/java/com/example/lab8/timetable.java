package com.example.lab8;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    int index;
    String sername;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable);
        try{
            DBHelper dbHelper = new DBHelper(this);
            dbHelper.create_db();
            dbtimetable = dbHelper.open();

            array = new TextView[] {
                    (TextView)findViewById(R.id.textView16),
                    (TextView)findViewById(R.id.textView17),
                    (TextView)findViewById(R.id.textView18),
                    (TextView)findViewById(R.id.textView19),
                    (TextView)findViewById(R.id.textView20),
                    (TextView)findViewById(R.id.textView22),
                    (TextView)findViewById(R.id.textView21),
            };

            type_week = getIntent().getStringExtra("type_week");
            day = getIntent().getStringExtra("day");

            index = getIntent().getIntExtra("index",2);
            switch (index)
            {
                case 0:
                    sername = getIntent().getStringExtra("sername");
                    setInformationForListSername();
                    break;
                case 1:
                    inst = getIntent().getStringExtra("inst");
                    spec = getIntent().getStringExtra("spec");
                    group = getIntent().getStringExtra("groupe");
                    setInformationForListGroup();
                    break;
                default:  Toast.makeText(this,"Не найден индекс перехода",Toast.LENGTH_LONG);break;
            }




        }
        catch (Exception e)
        {
          Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);
        }


    }

    private void setInformationForListSername()
    {
        try {
            String selection = "teacher = ? AND type_week = ? AND day_of_week = ?";
            cursor = dbtimetable.query(DBHelper.TABLE_CONTACT,null,selection,new String[] {sername,type_week,day},null,null,null);
            if(cursor.moveToFirst()){
                int numberIndex = cursor.getColumnIndex(DBHelper.KEY_NUMBER);
                int groupIndex = cursor.getColumnIndex(DBHelper.KEY_GROUP);
                int subjectIndex = cursor.getColumnIndex(DBHelper.KEY_SUBJECT);
                int placeIndex = cursor.getColumnIndex(DBHelper.KEY_PLACE);
                int typeSubIndex = cursor.getColumnIndex(DBHelper.KEY_TYPE_SUBJECT);
                int commentIndex = cursor.getColumnIndex(DBHelper.KEY_COMMENT);
                do{
                    Integer numberInd = cursor.getInt(numberIndex);
                    String gr = cursor.getString(groupIndex);
                    String sub = cursor.getString(subjectIndex);
                    String pl = cursor.getString(placeIndex);
                    String tp = cursor.getString(typeSubIndex);
                    String com = cursor.getString(commentIndex);
                    array[numberInd-1].setText(sub + "\n" + pl + " (" + tp + ") " + gr + "\n" + com);
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }
        catch (Exception e ){Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }
    private void setInformationForListGroup()
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
                        array[numberInd-1].setTextSize(11);
                        s = s +"\n" + "-----" + "\n" + sub + "\n" + pl + "("+ tp + ") " + teach + "\n" + com;
                        array[numberInd-1].setText(s);
                    }

                }
                while(cursor.moveToNext());
            }
            cursor.close();
        }
        catch (Exception e){ Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }
    @Override
    public void onPause() {
        super.onPause();
        dbtimetable.close();
        finish();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Закрываем подключение
        dbtimetable.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.about_us:
                startActivity(new Intent(timetable.this,about_us.class));
                return true;
            case R.id.help:
                Intent intent1 = new Intent(timetable.this, forHelper.class);
                startActivity(intent1);
                return true;
            case R.id.exit:
                Intent intent3 = new Intent(timetable.this,MainActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent3.putExtra("EXIT",true);
                startActivity(intent3);
                return true;
            default:
                return true;
        }
    }
}
