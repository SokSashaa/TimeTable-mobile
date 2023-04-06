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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class choosing_institute extends AppCompatActivity {
    SQLiteDatabase dbInstitute;
    ListView list_institutes;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayListInstitutes = new ArrayList<String>();
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_institute);

        try{
            DBHelperForInstitute dbHelperForInstitute = new DBHelperForInstitute(this);
            dbInstitute =dbHelperForInstitute.getWritableDatabase();
            list_institutes = (ListView)findViewById(R.id.list_institutes);
            setInformationForList();
            adapter=new ArrayAdapter<String>(this, R.layout.list_item,R.id.textVSpec, arrayListInstitutes);
            list_institutes.setAdapter(adapter);

        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    private void setInformationForList()
    {
        try{
            cursor = dbInstitute.query(DBHelperForInstitute.TABLE_CONTACT,null,null,null,null,null,null);

            if(cursor.moveToFirst()){
                int nameIndex = cursor.getColumnIndex(DBHelperForInstitute.KEY_NAME);
                do{arrayListInstitutes.add(cursor.getString(nameIndex));}
                while(cursor.moveToNext());
            }
            cursor.close();
        }
        catch (Exception e){ Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }
    public void SendInstitutes(View v)
    {
        switch (v.getId())
        {
            case R.id.layout_spec:
                Integer index = list_institutes.getPositionForView(v);
                String institute =arrayListInstitutes.get(index);
                Intent intent = new Intent(choosing_institute.this,choosing_speciality.class);
                intent.putExtra("institute",institute);
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
            case R.id.menu_base:
                return true;
            case R.id.help:
                return true;
            case R.id.exit:
                return true;
            default:
                return true;
        }
    }
}
