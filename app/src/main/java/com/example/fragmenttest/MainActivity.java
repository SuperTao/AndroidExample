package com.example.fragmenttest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;

    Fragment fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);

        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    fr = new Fragment1();
                    break;
                case R.id.button2:
                    fr = new Fragment2();
                    break;
            }
            // 管理Fragment，需要使用FragmentManager。
            FragmentManager fm = getFragmentManager();
            // 获得FragmentTransaction。 操作会用到其中的API。
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            // 替换
            fragmentTransaction.replace(R.id.fragment_place, fr);
            // 提交更改,将操作应用到activity中
            fragmentTransaction.commit();

        }
    };

}
