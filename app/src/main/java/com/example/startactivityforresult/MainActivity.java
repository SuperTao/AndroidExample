package com.example.startactivityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    TextView tv1;
    TextView tv2;
    int requestCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = (Button) findViewById(R.id.btnChangeText1);
        btn2 = (Button) findViewById(R.id.btnChangeText2);

        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnChangeText1:
                    requestCode = 1;
                    Intent intent1 = new Intent(MainActivity.this, Activity2.class);
                    intent1.putExtra("action", "Button1 pressed");
                    startActivityForResult(intent1, requestCode);
                    Log.e("test", "btn 1");
                    break;

                case R.id.btnChangeText2:
                    requestCode = 2;
                    Intent intent2 = new Intent(MainActivity.this, Activity2.class);
                    intent2.putExtra("action", "Button2 pressed");
                    startActivityForResult(intent2, requestCode);
                    Log.e("test", "btn 2");
                    break;

                default:
                    break;
            }

        }
    };
    /*
     * 获取上一个activity返回的数据。
     * requestcode: 调用startActivityForResult时，传入的参数的，返回的时候也会穿这个参数。
     * resultCode:  setResult()时传入的数据。表示操作是否成功。有多重参数，根据不同的记过进行操作。
     * data:        返回的数据。
     */
    @Override
    protected void onActivityResult(int requestcode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String str = data.getStringExtra("action");
            switch (requestCode) {
                case 1:
                    tv1 = (TextView) findViewById(R.id.textView1);
                    tv1.setText(str);
                    break;
                case 2:
                    tv2 = (TextView) findViewById(R.id.textView2);
                    tv2.setText(str);
                    break;
            }
        }
        else if (resultCode == RESULT_CANCELED) {

        }
    }
}
