package com.example.actionbartest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pass:
                Toast.makeText(this, "Pass", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.fail:
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.setting:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.configure:
                Toast.makeText(this, "configure", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
