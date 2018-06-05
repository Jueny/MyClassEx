package com.example.ex11_1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.text.BidiFormatter;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //获取广播中的数据
        String message=intent.getStringExtra("message");
        int index=intent.getIntExtra("index",0);
        //创建PendingIntent对象,"延缓"的意图
        Intent myIntent =new Intent(context,NotificationActivity.class);
        myIntent.putExtra("message",message);
        PendingIntent pi=PendingIntent.getActivity(context,index,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
       //创建通知
        Notification notification=new NotificationCompat.Builder(context)
//        NotificationCompat.Builder notification=new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setContentTitle("标题")
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                //getResources()；得到图片源的位置
                //decodeResource()对图片进行重新编码
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        //获取通知管理器
        NotificationManager manager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(index,notification);
    }
}
