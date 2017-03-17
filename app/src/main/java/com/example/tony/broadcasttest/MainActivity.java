package com.example.tony.broadcasttest;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button sendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBroadcast = (Button) findViewById(R.id.send_broadcast);
        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               broadcastIntent(v);
            }
        });
        // 手动注册广播，在AndroidManifest.xml中注册了就不需要手动注册
/*
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.CUSTOM_INTENT");
        registerReceiver(new MyReceiver(), intentFilter);
        */

    }

    public void broadcastIntent(View view) {
        Intent intent = new Intent();
        // 发送自己广播，名称自己定义，只要在AndroidManifest.xmL中注册，让Receive接受即可。
        intent.setAction("com.example.CUSTOM_INTENT");
        sendBroadcast(intent);
        Log.i("sendBraodcast", "braodcastintent");
    }
}
