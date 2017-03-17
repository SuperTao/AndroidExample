package com.example.startactivityforresult;

import android.content.ContentUris;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    TextView txtV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // 接受上一个activity发过来的数据并显示。
        Intent intent = getIntent();
        String str = intent.getStringExtra("action");
        txtV = (TextView) findViewById(R.id.textViewActivity2);
        txtV.setText(str);

        // 发送接收到的数据，发还给上一个activity。
        Intent intentBack = new Intent();
        intentBack.putExtra("action", str);
        setResult(RESULT_OK, intentBack);
//        setResult(RESULT_CANCELED, intentBack);
        // 如果立即调用finish()，这个activity会马上关闭，返回到前一个activity。所以不再这里调用。放到onStop()中调用。
//        this.finish();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        this.finish();
        Toast.makeText(getBaseContext(), "activity2 stop", Toast.LENGTH_SHORT).show();
    }
}
