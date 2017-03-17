package com.example.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private Button btn;
    public static String[] names = {"Android", "IPhone", "WindowsMobile", "Blackberry",
                        "WebOs", "Ubuntu", "Windows7", "Mac OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        MyBaseAdapter mAdapter = new MyBaseAdapter();

        mListView.setAdapter(mAdapter);
        // ListView的item被点击监听
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), names[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyBaseAdapter extends BaseAdapter {
        // 获得item的个数
        public int getCount() {
            return names.length;
        }
        // 获取item的对象
        public Object getItem(int position) {
            return names[position];
        }
        // 获取item的id
        public long getItemId(int position) {
            return position;
        }
        // 得到item的view视图
        public View getView(final int position, View convertView, ViewGroup parent) {
            // 由于在不同的布局文件中，所以要通过inflate动态加载到activity_main中。
            View view = View.inflate(MainActivity.this, R.layout.listview_item, null);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(names[position]);

            Button btn = (Button) view.findViewById(R.id.btn);
            // 设置button没有焦点，否则点击listview会无效。
            btn.setFocusable(false);
            btn.setText(names[position]);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getBaseContext(), "btn " + names[position] + " pressed", Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }
}
