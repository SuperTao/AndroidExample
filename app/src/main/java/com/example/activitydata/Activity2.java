package com.example.activitydata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    TextView tvName;
    TextView tvGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        tvName = (TextView) findViewById(R.id.textViewName);
        tvGrade = (TextView) findViewById(R.id.textViewGrade);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String grade = intent.getStringExtra("grade");

        tvName.setText("name  :" + name);
        tvGrade.setText("grade :" + grade);
    }
}
