package com.example.ex3_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv1=(TextView)findViewById(R.id.textView2);
        tv2=(TextView)findViewById(R.id.textView3);
        tv3=(TextView)findViewById(R.id.textView4);
        //获取意图
        Intent intent=getIntent();
        String height=intent.getStringExtra("height");
        String sex=intent.getStringExtra("sex");
        double weight;
        if(sex.equals("男")){
            weight=(Integer.parseInt(height)-80)*0.7;
        }else{
            weight=(Integer.parseInt(height)-70)*0.6;
        }
        tv1.setText("你的性别是"+sex);
        tv2.setText("你的身高是"+height);
        tv3.setText("标准体重应该是"+weight);
    }
}
