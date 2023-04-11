package com.example.lab8;

import android.content.Intent;
import android.content.res.Resources;
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

public class choosing_week extends AppCompatActivity {
    LinearLayout layout;
    ListView list_week;
    String inst;
    String spec;
    String group;
    String sername;
    ArrayAdapter<String> adapter;
    String[] week;
    int index;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_week);
        try{
            index = getIntent().getIntExtra("index", 2);
            switch (index) {
                case 0:
                    sername = getIntent().getStringExtra("sername");
                    createLayoutForSername();
                    break;
                case 1:
                    inst = getIntent().getStringExtra("inst");
                    spec = getIntent().getStringExtra("spec");
                    group = getIntent().getStringExtra("groupe");
                    createLayoutForGroupe();
                    break;
                default:
                    Toast.makeText(this,"Не найден индекс перехода",Toast.LENGTH_LONG).show();
                    break;
            }
            Resources res = getResources();
            week = res.getStringArray(R.array.week);
            adapter = new ArrayAdapter<String>(this, R.layout.list_item_week, R.id.textVSpec, week);
            list_week.setAdapter(adapter);
        }
        catch (Exception e)
        {Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);}


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
    private void createLayoutForSername()
    {
        layout = (LinearLayout)findViewById(R.id.layoutWeek);
        TextView textSername = new TextView(this);
        textSername.setText("Фамилия: " +sername);
        textSername.setTextSize(20);
        layout.addView(textSername);
        list_week = new ListView(this);
        layout.addView(list_week);
    }

    public void sendWeek(View view)
    {
            switch (view.getId())
            {
                case R.id.layout_spec:
                    Integer indexpos = list_week.getPositionForView(view);
                    String type_week = week[indexpos];
                    Intent intent = new Intent(choosing_week.this,choosing_day_of_the_week.class);
                    if(index==0)
                    {
                        intent.putExtra("index",0);
                        intent.putExtra("sername",sername);
                        intent.putExtra("type_week",type_week);
                    }
                    if(index==1)
                    {
                        intent.putExtra("index",1);
                        intent.putExtra("inst",inst);
                        intent.putExtra("spec",spec);
                        intent.putExtra("groupe",group);
                        intent.putExtra("type_week",type_week);

                    }
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
                startActivity(new Intent(choosing_week.this,about_us.class));
                return true;
            case R.id.help:
                Intent intent1 = new Intent(choosing_week.this,forHelper.class);
                startActivity(intent1);
                return true;
            case R.id.exit:
                Intent intent3 = new Intent(choosing_week.this,MainActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent3.putExtra("EXIT",true);
                startActivity(intent3);
                return true;
            default:
                return true;
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        finish();
    }
}
