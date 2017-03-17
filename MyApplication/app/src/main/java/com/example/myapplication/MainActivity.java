package com.example.myapplication;

import android.content.ContentUris;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;

import android.content.ContentValues;
import android.content.CursorLoader;

import android.database.Cursor;

import android.view.Menu;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME,
                ((EditText)findViewById(R.id.editTextName)).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText)findViewById(R.id.editTextGrade)).getText().toString());
        /*
         *  通过getContentResolver()获取一个ContentResolver的对象。
         *  再通过contentResolver类进行数据插入
         */
        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }
    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.myapplication.StudentsProvider/students";
        // 将String转为URi对象
        // Uri:"content://com.example.myapplication.StudentsProvider/students";
        Uri students = Uri.parse(URL);
        // 在URI后面添加一个ID
        // 如果这行注释了，那么匹配students表里面的所有项
        // Uri:"content://com.example.myapplication.StudentsProvider/students/1";
        students = ContentUris.withAppendedId(students, 1);
        /*
         *  数据查询
         *  返回数据库游标接口Cursor。
         */
//        Cursor c = managedQuery(students, null, null, null, null);
        Cursor c = getContentResolver().query(students, null, null, null, null);

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
