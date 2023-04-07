package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Send(View view)
    {
        switch (view.getId())
        {
            case R.id.buttonGroupe:
                Intent intent = new Intent(MainActivity.this,choosing_institute.class);
                startActivity(intent);
                break;
            case R.id.buttonTeacher:
                Intent intent1 = new Intent(MainActivity.this,choosing_teachers_sername.class);
                startActivity(intent1);
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
                Intent intent1 = new Intent(MainActivity.this,forHelper.class);
                startActivity(intent1);
                return true;
            case R.id.exit:
                return true;
            default:
                return true;
        }
    }

}