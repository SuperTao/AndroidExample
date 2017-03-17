package com.example.menutest;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CONF_ITEM = Menu.FIRST;
    private static final int CONF_ITEM_1= Menu.FIRST + 1;
    private static final int CONF_ITEM_2 = Menu.FIRST + 2;
    private static final int STOP_ITEM = Menu.FIRST + 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        int groupId = 0;

        /*
         * 添加子菜单,并在子菜单中添加选项
         * addSubMenu(final int groupId, final int itemId, int order, final CharSequence title);
         * groupId: 组ID,有时候会将菜单放在同一个组中，同时对组进行相关的设置。
         * itemId:  选项的id。点击选项的时候会根据这个来进行区分。
         * order:   顺序
         * title:   选项的标题
         */
        SubMenu sub1 = menu.addSubMenu(groupId, CONF_ITEM, Menu.NONE, "CONF");
        /*
         * 为子菜单中添加选项。
         * add(int groupId, int itemId, int order, CharSequence title);
         * groupId: 组ID,有时候会将菜单放在同一个组中，同时对组进行相关的设置。
         * itemId:  选项的id。点击选项的时候会根据这个来进行区分。
         * order:   顺序
         * title:   选项的标题
         */
        sub1.add(groupId, CONF_ITEM_1, Menu.NONE, "CONF_1");
        sub1.add(groupId, CONF_ITEM_2, Menu.NONE, "CONF_2");

        // 添加一个菜单选项
        menu.add(groupId + 1, STOP_ITEM, Menu.NONE, "STOP");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.start:
                Toast.makeText(getBaseContext(), "start", Toast.LENGTH_SHORT).show();
                break;

            case R.id.set:
                Toast.makeText(getBaseContext(), "setting", Toast.LENGTH_SHORT).show();
                break;

            case R.id.set1:
                Toast.makeText(getBaseContext(), "set 1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.set2:
                Toast.makeText(getBaseContext(), "set 2", Toast.LENGTH_SHORT).show();
                break;

            case STOP_ITEM:
                Toast.makeText(getBaseContext(), "stop", Toast.LENGTH_SHORT).show();
                break;

            case CONF_ITEM:
                Toast.makeText(getBaseContext(), "conf", Toast.LENGTH_SHORT).show();
                break;

            case CONF_ITEM_1:
                Toast.makeText(getBaseContext(), "conf 1", Toast.LENGTH_SHORT).show();
                break;

            case CONF_ITEM_2:
                Toast.makeText(getBaseContext(), "conf 2", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
