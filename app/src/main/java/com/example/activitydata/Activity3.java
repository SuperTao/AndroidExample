package com.example.activitydata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {
    TextView tvName;
    TextView tvGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        tvName = (TextView) findViewById(R.id.textViewName3);
        tvGrade = (TextView) findViewById(R.id.textViewGrade3);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        String grade = bundle.getInt("grade") + "";

        tvName.setText("name  :" + name);
        tvGrade.setText("grade :" + grade);
    }
}
