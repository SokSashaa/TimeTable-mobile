package com.example.lab8;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
            dbGroupes =dbHelperGroupe.getWritableDatabase();
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
        catch (Exception e){ Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }

}
