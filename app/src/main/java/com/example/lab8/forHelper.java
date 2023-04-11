package com.example.lab8;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        openFile();
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
    private void openFile() {
        try {
            String fileName ="help.txt";
            //AssetFileDescriptor descriptor = getAssets().openFd("help.txt");
            InputStream inputStream = getAssets().open(fileName);

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                StringBuilder builder = new StringBuilder();
                int i = 0;
                int j = 0;
                while ((line = reader.readLine()) != null) {
                    String[] s = line.split( "/");
                    month[i].setText(s[0]);i++;
                    String[] s1 = s[1].split(">");
                    day[j].setText(s1[0]);j++;
                    day[j].setText(s1[1]);j++;
                }
                inputStream.close();
            }
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
