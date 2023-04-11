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

public class choosing_teachers_sername extends AppCompatActivity {

    ListView list_sername;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayListsername = new ArrayList<String>();
    Cursor cursor;
    SQLiteDatabase dbTeacher;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_teachers_sername);

        try{
            DBHelperTeacher dbHelperTeacher = new DBHelperTeacher(this);
            dbHelperTeacher.create_db();
            dbTeacher = dbHelperTeacher.open();
            list_sername = (ListView)findViewById(R.id.list_sername);
            setInformationForList();
            adapter=new ArrayAdapter<String>(this, R.layout.list_item_sername,R.id.textSername, arrayListsername);
            list_sername.setAdapter(adapter);
        }
        catch (Exception e){}
    }
    private void setInformationForList()
    {
        try{

            cursor = dbTeacher.query(DBHelperTeacher.TABLE_CONTACT,null,null,null,null,null,null);


            if(cursor.moveToFirst()){
                int nameIndex = cursor.getColumnIndex(DBHelperTeacher.KEY_NAME);
                do{arrayListsername.add(cursor.getString(nameIndex));}
                while(cursor.moveToNext());
            }
            cursor.close();
        }
        catch (Exception e){ Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();}
    }
    public void sendSername(View v)
    {
        switch (v.getId())
        {
            case R.id.layout_sername:
                Integer index = list_sername.getPositionForView(v);
                String sername =arrayListsername.get(index);
                Intent intent = new Intent(choosing_teachers_sername.this,choosing_week.class);
                intent.putExtra("sername",sername);
                intent.putExtra("index",0);
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
                Intent intent1 = new Intent(choosing_teachers_sername.this,forHelper.class);
                startActivity(intent1);
                return true;
            case R.id.exit:
                Intent intent3 = new Intent(choosing_teachers_sername.this,MainActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent3.putExtra("EXIT",true);
                startActivity(intent3);
                return true;
            default:
                return true;
        }
    }

}
