package com.example.lab8;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class choosing_groupes extends AppCompatActivity {

    SQLiteDatabase dbGroupes;
    ListView list_groupes;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayListGroupe = new ArrayList<String>();
    Cursor cursor;
    TextView institute;
    TextView spec;
    String inst;
    String specs;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_groups);

        institute = (TextView)findViewById(R.id.textG_Inst);
        spec = (TextView)findViewById(R.id.textG_Spec);
        inst =getIntent().getStringExtra("inst");
        institute.setText("Ваш институт: " + inst);
        specs = getIntent().getStringExtra("spec");
        spec.setText("Ваше направление: " + specs);

        try{
            DBHelperGroupe dbHelperGroupe = new DBHelperGroupe(this);
            dbHelperGroupe.create_db();
            dbGroupes =dbHelperGroupe.open();
            list_groupes = (ListView) findViewById(R.id.list_groups);
            setInformationForList();
            adapter=new ArrayAdapter<String>(this, R.layout.list_item_groupe,R.id.textVSpec, arrayListGroupe);
            list_groupes.setAdapter(adapter);
        }
        catch (Exception e) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);
        }
    }
    private void setInformationForList()
    {
        String selection = "institute = ? AND spec = ? ";
        try{

            cursor = dbGroupes.query(DBHelperGroupe.TABLE_CONTACT,null,selection,new String[] {inst,specs},null,null,null);


            if(cursor.moveToFirst()){
                int nameIndex = cursor.getColumnIndex(DBHelperGroupe.KEY_NAME);
                do{arrayListGroupe.add(cursor.getString(nameIndex));}
                while(cursor.moveToNext());
            }
            cursor.close();
        }
        catch (Exception e){Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }
    public void sendGroup(View v)
    {
        switch (v.getId())
        {
            case R.id.layout_spec:
                Integer index = list_groupes.getPositionForView(v);
                String groupe =arrayListGroupe.get(index);
                Intent intent = new Intent(choosing_groupes.this,choosing_week.class);
                intent.putExtra("inst",inst);
                intent.putExtra("spec",specs);
                intent.putExtra("groupe",groupe);
                intent.putExtra("index",1);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Закрываем подключение
        dbGroupes.close();
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
                startActivity(new Intent(choosing_groupes.this,about_us.class));
                return true;
            case R.id.help:
                Intent intent1 = new Intent(choosing_groupes.this, forHelper.class);
                startActivity(intent1);
                return true;
            case R.id.exit:
                Intent intent3 = new Intent(choosing_groupes.this,MainActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent3.putExtra("EXIT",true);
                startActivity(intent3);
                return true;
            default:
                return true;
        }
    }


}
