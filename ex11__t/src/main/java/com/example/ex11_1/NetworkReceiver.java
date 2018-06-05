package com.example.ex11_1;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/5/29.
 */

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    //ConnectivityManager网络连接管理器
    public void onReceive(final Context context, Intent intent) {
        ConnectivityManager manager=(ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //.getActiveNetworkInfo()获取可用的网络信息
        NetworkInfo info=manager.getActiveNetworkInfo();
        if(info!=null&&info.isAvailable()){//isAvailable是否可用
            Toast.makeText(context,"当前网络为："+info.getTypeName()+"/"
                    +info.getSubtypeName(),Toast.LENGTH_LONG).show();
        }else {
            AlertDialog dialog=new AlertDialog.Builder(context).setTitle("网络设置")
                    .setMessage("当前网络不可用,是否设置")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //创建隐式的意图：跳转到目标action的活动
                            Intent myIntent=new Intent("android.settings.WIRELESS_SETTINGS");
                            context.startActivity(myIntent);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();//取消dialog
                        }
                    }).create();
            dialog.show();
        }
    }
}
