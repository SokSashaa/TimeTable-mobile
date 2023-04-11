package com.example.lab8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;

public class about_us extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

    }
    public void Send(View view)
    {
        switch (view.getId())
        {
            case R.id.button:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/soksasha"));
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/slepojboy"));
                startActivity(intent1);
                break;
        }
    }
}
