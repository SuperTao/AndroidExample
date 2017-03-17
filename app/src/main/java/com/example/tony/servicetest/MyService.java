package com.example.tony.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public static final String TAG = "MyService";

    private MyBinder mBinder = new MyBinder();
    // 服务首次创建的时候会运行onCreate
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate() executed");
        Toast.makeText(this, "Service create", Toast.LENGTH_LONG).show();
    }
    // 服务启动时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartcommand() executed");
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind() executed");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent){
        Log.i(TAG, "onUnbind() executed");
        /*
         * 这里需要return true,activity再次进行bind的时候才会调用onRebind函数。
         * return false不能调用，return super.onUnbind(intent)我试了也不能调用onRebind函数。
         */
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, "onRebind() executed");
    }

    // 服务停止时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() executed");
        Toast.makeText(this, "Service Destroy", Toast.LENGTH_LONG).show();
    }

    class MyBinder extends Binder {
        // 自己实现的一个函数，绑定成功的话，在activity中就可以调用Binder类中的函数。
        public void startDownload() {
            Log.i(TAG, "start download() executed");
        }
    }
}
