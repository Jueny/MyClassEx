package com.example.john.juxenyapplication;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button previous,next;
    private TextView tv ,text;
    private ImageView imageView;

    private int[] imgIds = new int[] { R.drawable.test1, R.drawable.test2,
            R.drawable.test3, R.drawable.test4, R.drawable.test5};
    private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previous = (Button) findViewById(R.id.btn_previous);
        next = (Button) findViewById(R.id.btn_next);
        imageView = (ImageView) findViewById(R.id.imageview);
        tv = (TextView) findViewById(R.id.textView);
        text=(TextView)findViewById(R.id.textView1);
        showInfo();
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("");
                if(index == 0){
                    text.setText("这是第一张了");
                    return;
                }
                index--;showInfo();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("");
                if(index == imgIds.length-1){
                    text.setText("这是最后张了");
                    return;
                }
                index++;showInfo();
            }
        });

    }
    public void showInfo() {
        imageView.setImageResource(imgIds[index]);
        tv.setText((index + 1) + "/" + imgIds.length);
        }
}
