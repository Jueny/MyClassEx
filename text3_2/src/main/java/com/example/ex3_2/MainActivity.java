package com.example.ex3_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    EditText et;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg=(RadioGroup)findViewById(R.id.radioGroup);
        et=(EditText)findViewById(R.id.editText);
        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据
                String height=et.getText().toString();
                //TextUtils.isEmpty():检查null和空字符串
                if(TextUtils.isEmpty(height)){
                    Toast.makeText(MainActivity.this,"请输入身高",Toast.LENGTH_SHORT).show();
                    return;
                }
                //通过管理组获得被选中RadioButton的id
                int id=rg.getCheckedRadioButtonId();
                RadioButton rb=(RadioButton)findViewById(R.id.radioButton);
                String sex=rb.getText().toString();
                //创建意图
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("height",height);
                intent.putExtra("sex",sex);
                startActivity(intent);
            }
        });
    }
}
