package com.example.lab8;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class forHelper extends AppCompatActivity {

    TextView[] month;
    TextView[] day;
    Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_helper);

        month = new TextView[] {
                (TextView)findViewById(R.id.textView26),
                (TextView)findViewById(R.id.textView29),
                (TextView)findViewById(R.id.textView32),
                (TextView)findViewById(R.id.textView35),
                (TextView)findViewById(R.id.textView38)
        };
        day = new TextView[] {
                (TextView)findViewById(R.id.textView27),
                (TextView)findViewById(R.id.textView28),
                (TextView)findViewById(R.id.textView30),
                (TextView)findViewById(R.id.textView31),
                (TextView)findViewById(R.id.textView33),
                (TextView)findViewById(R.id.textView34),
                (TextView)findViewById(R.id.textView36),
                (TextView)findViewById(R.id.textView37),
                (TextView)findViewById(R.id.textView39),
                (TextView)findViewById(R.id.textView40)
        };
    }
    private void open_file() {
        String name_file ="help.txt";
        File file = new File(name_file);
        if(!file.exists())
        {
            try {
                InputStream myInput = context.getAssets().open(name_file);
            }
            catch (Exception e){

            }
        }
    }
}
