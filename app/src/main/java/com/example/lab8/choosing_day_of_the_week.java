package com.example.lab8;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class choosing_day_of_the_week extends AppCompatActivity {

    LinearLayout layout;
    ListView list_days;
    ArrayAdapter<String> adapter;
    String inst;
    String spec;
    String group;
    String type_week;
    String[] days;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_day_of_the_week);
        inst = getIntent().getStringExtra("inst");
        spec = getIntent().getStringExtra("spec");
        group = getIntent().getStringExtra("groupe");
        type_week=getIntent().getStringExtra("type_week");

        try{
            Resources res = getResources();
            days = res.getStringArray(R.array.days_of_week);
            adapter=new ArrayAdapter<String>(this, R.layout.list_item_days_week,R.id.textVSpec,days);}
        catch (Exception e ){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);
        }


        int index = getIntent().getIntExtra("index",2);
        switch (index){
            case 0:
                createLayoutForGroupe();
                list_days.setAdapter(adapter);
                break;
            case 1:
                createLayoutForTeacher();
                list_days.setAdapter(adapter);
                break;
            default: break;
        }

    }
    private void createLayoutForGroupe()
    {
        layout = (LinearLayout)findViewById(R.id.week_layout);
        TextView textInst = new TextView(this);
        textInst.setText("Ваш институт: " +inst);
        textInst.setTextSize(20);
        layout.addView(textInst);
        TextView textSpec = new TextView(this);
        textSpec.setText("Ваше направление: " + spec);
        textSpec.setTextSize(20);
        layout.addView(textSpec);
        TextView textGroupe = new TextView(this);
        textGroupe.setText("Ваша группа: " + group );
        textGroupe.setTextSize(20);
        layout.addView(textGroupe);
        TextView textType = new TextView(this);
        textType.setText("Тип недели: " + type_week );
        textType.setTextSize(20);
        layout.addView(textType);
        list_days = new ListView(this);
        layout.addView(list_days);
    }

    private void createLayoutForTeacher()
    {
        layout = (LinearLayout)findViewById(R.id.week_layout);
        TextView textTeacher = new TextView(this);
        textTeacher.setText("Фамилия преподавателя: " );
        textTeacher.setTextSize(20);
        layout.addView(textTeacher);
        list_days = new ListView(this);
        layout.addView(list_days);
    }

    public void sendDay(View v)
    {
        switch (v.getId())
        {
            case R.id.layout_spec:
                Integer index = list_days.getPositionForView(v);
                String day =days[index];
                Intent intent = new Intent(choosing_day_of_the_week.this,timetable.class);
                intent.putExtra("inst",inst);
                intent.putExtra("spec",spec);
                intent.putExtra("groupe",group);
                intent.putExtra("type_week",type_week);
                intent.putExtra("day",day);
                startActivity(intent);
                break;
        }
    }
}
