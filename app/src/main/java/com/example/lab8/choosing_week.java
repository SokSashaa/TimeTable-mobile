package com.example.lab8;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class choosing_week extends AppCompatActivity {
    LinearLayout layout;
    ListView list_week;
    String inst;
    String spec;
    String group;
    ArrayAdapter<String> adapter;
    String[] week;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_week);

        inst = getIntent().getStringExtra("inst");
        spec = getIntent().getStringExtra("spec");
        group = getIntent().getStringExtra("groupe");
try{ createLayoutForGroupe();

    Resources res = getResources();
    week = res.getStringArray(R.array.week);
    adapter=new ArrayAdapter<String>(this, R.layout.list_item_week,R.id.textVSpec,week);
    list_week.setAdapter(adapter);
}
catch (Exception e) {}

    }

    private void createLayoutForGroupe()
    {
        try{
            layout = (LinearLayout)findViewById(R.id.layoutWeek);
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
            list_week = new ListView(this);
            layout.addView(list_week);
        }
        catch (Exception r){}

    }

    public void sendWeek(View view)
    {
            switch (view.getId())
            {
                case R.id.layout_spec:
                    Integer index = list_week.getPositionForView(view);
                    String type_week = week[index];
                    Intent intent = new Intent(choosing_week.this,choosing_day_of_the_week.class);
                    intent.putExtra("index",0);
                    intent.putExtra("inst",inst);
                    intent.putExtra("spec",spec);
                    intent.putExtra("groupe",group);
                    intent.putExtra("type_week",type_week);
                    startActivity(intent);
                    break;
            }
    }
}