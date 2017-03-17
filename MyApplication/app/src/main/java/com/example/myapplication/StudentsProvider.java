package com.example.myapplication;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

import android.database.Cursor;
import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/*
 * 系统实例化所有的ContentProvider对象；你从来不需要自己做。
 * 事实上，你从来不会直接处理ContentProvider对象。
 * 通常，对于每个类型的ContentProvider只有一个简单的实例。
 */
public class StudentsProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.myapplication.StudentsProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/students";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String NAME = "name";
    static final String GRADE = "grade";

    private static HashMap<String, String> STUDENTS_PROJECTION_MAP;

    static final int STUDENTS = 1;
    static final int STUDENT_ID = 2;

    static final UriMatcher uriMatcher;
    // 设置匹配规则
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 第一个参数：authority,第二个参数：path
        // 第三个参数:code, 匹配成功之后的返回值
        /* 结合authority和path。下面设置的匹配规则
            com.example.myapplication.StudentsProvider/students/
            表示匹配students这个项，而students包含了很多的id。
            匹配的结果是一个集合
         */
        uriMatcher.addURI(PROVIDER_NAME, "students", STUDENTS);
        // com.example.myapplication.StudentsProvider/students/1
        // student/后面的数字表示一个id.也可以是其他数字
        // 匹配的结果是特定的某一个实例
        uriMatcher.addURI(PROVIDER_NAME, "students/#", STUDENT_ID);
    }

    /**
     * Database specific constant declarations
     */

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "College";
    static final String STUDENTS_TABLE_NAME = "students";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + STUDENTS_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL, " +
                    " grade TEXT NOT NULL);";

    /**
     * Helper class that actually creates and manages
     * the provider's underlying data repository.
     */
    // 创建数据库的匿名类
    private static class DatabaseHelper extends SQLiteOpenHelper {
        // 构造方法, 一般传入需要创建的数据库名称。
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        // 创建数据库时调用
        @Override
        public void onCreate(SQLiteDatabase db) {
            // 初始化数据表结构，执行一条建表语句
            db.execSQL(CREATE_DB_TABLE);
        }
        // 数据库版本更新时调用
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  STUDENTS_TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        // 创建数据库对象
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */
        // 获取一个可读可写的数据库
        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * Add a new student record
         */
        // 数据库插入
        long rowID = db.insert(	STUDENTS_TABLE_NAME, "", values);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            // 通知注册在此URI上的访问者，监听contentprovider的变化
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection,String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(STUDENTS_TABLE_NAME);
        // 根据不同的匹配规则进行处理
        Log.e("TEST", "query");
        switch (uriMatcher.match(uri)) {
            // 匹配某一类，返回的是一个集合
            case STUDENTS:
                qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
                Log.e("TEST", "STUDENTS");
                break;
            // 匹配某一个，返回的是一个特定的实例
            case STUDENT_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                Log.e("TEST", "STUDENTS_ID");
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = NAME;
        }

        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        /*
         * Cursor类的setNotificationUri函数来把当前上下文的ContentResolver对象保存到Curosr里面去，
         * 以便当通过这个Cursor来改变数据库中的数据时，可以通知相应的ContentObserver来处理。
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case STUDENTS:
                count = db.delete(STUDENTS_TABLE_NAME, selection, selectionArgs);
                break;

            case STUDENT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete( STUDENTS_TABLE_NAME, _ID +  " = " + id +
                                (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        // 通知那些注册了监控特定URI的ContentObserver对象，使得它们可以相应地执行一些处理，例如更新数据在界面上的显示。
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                count = db.update(STUDENTS_TABLE_NAME, values, selection, selectionArgs);
                break;

            case STUDENT_ID:
                count = db.update(STUDENTS_TABLE_NAME, values,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ?
                                 " AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd.example.students";
            /**
             * Get a particular student
             */
            case STUDENT_ID:
                return "vnd.android.cursor.item/vnd.example.students";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}
