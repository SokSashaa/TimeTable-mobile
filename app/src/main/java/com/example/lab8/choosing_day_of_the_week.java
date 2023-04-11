package com.example.lab8;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    String sername;
    int index;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_day_of_the_week);
        Resources res = getResources();
        days = res.getStringArray(R.array.days_of_week);
        adapter=new ArrayAdapter<String>(this, R.layout.list_item_days_week,R.id.textVSpec,days);

        type_week=getIntent().getStringExtra("type_week");
        index = getIntent().getIntExtra("index",2);
        switch (index){
            case 0:
                sername = getIntent().getStringExtra("sername");

                createLayoutForTeacher();
                list_days.setAdapter(adapter);
                break;
            case 1:
                inst = getIntent().getStringExtra("inst");
                spec = getIntent().getStringExtra("spec");
                group = getIntent().getStringExtra("groupe");
                createLayoutForGroupe();
                list_days.setAdapter(adapter);
                break;
            default:   Toast.makeText(this,"Не найден индекс перехода",Toast.LENGTH_LONG).show();break;
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
        textTeacher.setText("Фамилия преподавателя: " + sername);
        textTeacher.setTextSize(20);
        layout.addView(textTeacher);
        TextView textType = new TextView(this);
        textType.setText("Тип недели: " + type_week );
        textType.setTextSize(20);
        layout.addView(textType);
        list_days = new ListView(this);
        layout.addView(list_days);
    }

    public void sendDay(View v)
    {
        switch (v.getId())
        {
            case R.id.layout_spec:
                Integer indexpos = list_days.getPositionForView(v);
                String day =days[indexpos];
                Intent intent = new Intent(choosing_day_of_the_week.this,timetable.class);
                intent.putExtra("index",index);
                if(index==0){
                    intent.putExtra("sername",sername);
                }
                if(index==1){
                    intent.putExtra("inst",inst);
                    intent.putExtra("spec",spec);
                    intent.putExtra("groupe",group);

                }
                intent.putExtra("type_week",type_week);
                intent.putExtra("day",day);
                startActivity(intent);
                break;
        }
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
                startActivity(new Intent(choosing_day_of_the_week.this,about_us.class));
                return true;
            case R.id.help:
                Intent intent1 = new Intent(choosing_day_of_the_week.this, forHelper.class);
                startActivity(intent1);
                return true;
            case R.id.exit:
                Intent intent3 = new Intent(choosing_day_of_the_week.this,MainActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent3.putExtra("EXIT",true);
                startActivity(intent3);
                return true;
            default:
                return true;
        }
    }
}
