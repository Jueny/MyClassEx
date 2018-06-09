package com.example.ex6_2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.ex6_2.task.ImageTask;
public class MainActivity extends AppCompatActivity {
    Button btn;
    ImageView picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picture=(ImageView)findViewById(R.id.pic);
        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImageTask(new ImageTask.CallBack() {
                    @Override
                    public void getDate(Bitmap result) {
                        picture.setImageBitmap(result);
                    }
                }).execute("http://www.baidu.com/img/bdlogo.png");
            }
        });
    }
}
