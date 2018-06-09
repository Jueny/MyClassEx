package com.example.ex6_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ex6_1.task.JSONTask;

public class MainActivity extends AppCompatActivity {
    Button load;
    TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load=(Button)findViewById(R.id.load);
        content=(TextView)findViewById(R.id.content);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONTask(new JSONTask.CallBack() {
                    @Override
                    public void getDate(String result) {
                        content.setText(result);
                    }
                })
                        .execute("https://hao.360.cn/?safe");
            }
        });
    }
}
