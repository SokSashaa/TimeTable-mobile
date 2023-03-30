package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

}