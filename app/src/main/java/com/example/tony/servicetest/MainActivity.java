package com.example.tony.servicetest;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startServiceButton;
    private Button stopServiceButton;
    private Button bindServiceButton;
    private Button unbindServiceButton;

    private MyService.MyBinder myBinder;

    /* 创建匿名类
     * 在里面重写了onServiceConnected()方法和onServiceDisconnected()方法，
     * 这两个方法分别会在Activity与Service建立关联和解除关联的时候调用
     */
    private ServiceConnection myConnection = new ServiceConnection() {
        /* bind成功之后，会运行onServiceConnected函数。
         * 这样就可以在activity中指挥service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();

            Log.i("MyActivity", "onServiceConnected() executed");
        }
        /*
         * 测试的时候，unbind时，并没有调用onServiceDisconnected函数，网上有解释如下，service异常断开连接的时候才会调用。
         * This is called when the connection with the service has been unexpectedly disconnected -- that is,
         * its process crashed. Because it is running in our same process, we should never see this happen.
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MyActivity", "onServiceDisconnected() executed");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startServiceButton = (Button) findViewById(R.id.startService);
        stopServiceButton = (Button) findViewById(R.id.stopService);
        bindServiceButton = (Button) findViewById(R.id.bindService);
        unbindServiceButton = (Button) findViewById(R.id.unbindService);

        startServiceButton.setOnClickListener(this);
        stopServiceButton.setOnClickListener(this);
        bindServiceButton.setOnClickListener(this);
        unbindServiceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startService:
                Log.i("MyActivity", "start_service button pressed");
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);      // 启动service
                break;
            case R.id.stopService:
                Log.i("MyActivity", "stop_service button pressed");
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);        // 停止service
                break;
            // 将Activity和Service进行关联
            case R.id.bindService:
                Log.i("MyActivity", "bind_service button pressed");
                Intent bindIntent = new Intent(this, MyService.class);
                /* 这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service，
                 * 这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行。
                 */
                bindService(bindIntent, myConnection, BIND_AUTO_CREATE);
                break;
            // 将Activity和Service解除关联
            case R.id.unbindService:
                Log.i("MyActivity", "unbind_service button pressed");
                unbindService(myConnection);
                break;
            default:
                break;
        }
    }
}
