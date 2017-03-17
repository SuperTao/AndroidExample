package com.example.activitydata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnIntent;
    Button btnBundle;

    EditText edtName;
    EditText edtGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.editTextName);
        edtGrade = (EditText) findViewById(R.id.editTextGrade);

        btnIntent = (Button) findViewById(R.id.btnIntentSend);
        btnBundle = (Button) findViewById(R.id.btnBundleSend);

        btnIntent.setOnClickListener(listener);
        btnBundle.setOnClickListener(listener);
    }

    private View.OnClickListener listener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btnIntentSend:
                    Intent intent = new Intent(MainActivity.this, Activity2.class);
                    // trim()函数用于去掉字符串首位的空格
                    intent.putExtra("name", edtName.getText().toString().trim());
                    intent.putExtra("grade", edtGrade.getText().toString().trim());

                    startActivity(intent);

                    break;
                case R.id.btnBundleSend:
                    // 使用Bundle传递数据
                    Intent i = new Intent(MainActivity.this, Activity3.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", edtName.getText().toString().trim());
                    bundle.putInt("grade", Integer.parseInt(edtGrade.getText().toString().trim()));
                    i.putExtras(bundle);
                    startActivity(i);
                    break;

                default:
                    break;
            }
        }
    };
}
