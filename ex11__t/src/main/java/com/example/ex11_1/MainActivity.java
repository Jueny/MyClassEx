package com.example.ex11_1;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    NetworkReceiver receiver;
    Button send;
    int index;
    LocalBroadcastManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建意图，包含广播的action（频道）
                Intent intent=new Intent("com.example.ex11_1.MYBROADCAST");//【com.example.ex11_1随意】频道（往往写包的名称）
                intent.putExtra("message","这是第"+index+"条广播的消息");
                intent.putExtra("index",index);
                index++;
                //发送全局广播
                sendBroadcast(intent);
//                //发送本地广播
//                manager=LocalBroadcastManager.getInstance(MainActivity.this);
//                manager.sendBroadcast(intent);
            }
        });
        //动态注册广播接收器
        //调频
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        receiver=new NetworkReceiver();
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);//注销接收器
    }
}
