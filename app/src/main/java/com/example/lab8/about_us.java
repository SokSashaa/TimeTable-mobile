package com.example.lab8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.help:
                Intent intent1 = new Intent(about_us.this, forHelper.class);
                startActivity(intent1);
                return true;
            case R.id.exit:
                Intent intent3 = new Intent(about_us.this,MainActivity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent3.putExtra("EXIT",true);
                startActivity(intent3);
                return true;
            default:
                return true;
        }
    }
}
