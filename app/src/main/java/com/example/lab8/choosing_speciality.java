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
            dbHelperSpec.create_db();
            dbSpeciality = dbHelperSpec.open();
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

    public void SendSpec(View v)
    {
        switch (v.getId())
        {
            case R.id.layout_spec:
                Integer index = list_spec.getPositionForView(v);
                String spec =arrayListSpec.get(index);
                Intent intent = new Intent(choosing_speciality.this,choosing_groupes.class);
                intent.putExtra("spec",spec);
                intent.putExtra("inst",inst);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Закрываем подключение
        dbSpeciality.close();
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
                startActivity(new Intent(choosing_speciality.this,about_us.class));
                return true;
            case R.id.help:
                Intent intent1 = new Intent(choosing_speciality.this, forHelper.class);
                startActivity(intent1);
                return true;
            case R.id.exit:
                Intent intent3 = new Intent(choosing_speciality.this,MainActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent3.putExtra("EXIT",true);
                startActivity(intent3);
                return true;
            default:
                return true;
        }
    }
}
