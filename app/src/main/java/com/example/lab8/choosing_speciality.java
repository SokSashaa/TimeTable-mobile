package com.example.lab8;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class choosing_speciality extends AppCompatActivity {
    SQLiteDatabase dbSpeciality;
    ListView list_spec;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayListSpec = new ArrayList<String>();
    Cursor cursor;
    TextView institute;
    String inst;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_speciality);

        institute = (TextView)findViewById(R.id.text_institute);
        inst =getIntent().getStringExtra("institute");
        institute.setText("Ваш институт: " + inst);

        try{
            DBHelperSpec dbHelperSpec = new DBHelperSpec(this);
            dbSpeciality =dbHelperSpec.getWritableDatabase();

            list_spec = (ListView)findViewById(R.id.list_specialitys);
            setInformationForList();
            adapter=new ArrayAdapter<String>(this, R.layout.list_item_spec,R.id.textVSpec, arrayListSpec);
            list_spec.setAdapter(adapter);
        }
        catch (Exception e) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG);
        }
    }
    private void setInformationForList()
    {
        String selection = "institute = ?";
        try{

            cursor = dbSpeciality.query(DBHelperSpec.TABLE_CONTACT,null,selection,new String[] {inst},null,null,null);


            if(cursor.moveToFirst()){
                int nameIndex = cursor.getColumnIndex(DBHelperSpec.KEY_NAME);
                do{arrayListSpec.add(cursor.getString(nameIndex));}
                while(cursor.moveToNext());
            }
            cursor.close();
        }
        catch (Exception e){ Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }
}
