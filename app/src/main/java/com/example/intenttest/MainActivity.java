package com.example.intenttest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button btnExplicit;
    public Button btnImplicit;
    public Button btnIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExplicit = (Button) findViewById(R.id.explicit);
        btnImplicit = (Button) findViewById(R.id.implicit);
        btnIntentFilter = (Button) findViewById(R.id.intentFilter);
        // 打开activity2
        btnExplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 根据指定类型开启组件
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                // 除了通过指定类型开启组件，还可以根据组件包名、全路径来指定开启组件。
                /*
                Intent intent = new Intent();
                intent.setClassName("com.example.intenttest","com.example.intenttest.Activity2");
                */
                startActivity(intent);
            }
        });
        // 打开浏览器，访问网址
        btnImplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.google.com"));
                startActivity(intent);
            }
        });
        // 打开相机
        btnIntentFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.media.action.IMAGE_CAPTURE");
                intent.addCategory("android.intent.category.DEFAULT");
                startActivity(intent);
            }
        });
    }
}
