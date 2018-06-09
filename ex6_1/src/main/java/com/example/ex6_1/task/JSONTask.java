package com.example.ex6_1.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2018/4/10.
 * 之前给网址，得到字符串
 */

public class JSONTask extends AsyncTask<String,Void,String > {
    public JSONTask(CallBack callBack) {
        this.callBack = callBack;
    }

    CallBack callBack;//时空穿梭机，子线程访问网络后将数据带回主线程
    @Override
    protected String doInBackground(String... params) {//...
        BufferedReader reader=null;
        HttpURLConnection conn=null;
        StringBuilder builder=new StringBuilder();
        try {
            URL url=new URL(params[0]);
             conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            InputStream is=conn.getInputStream();
             reader=new BufferedReader(new InputStreamReader(is));  //将字节流转换成带缓冲的字符流
            String line;

            while ((line=reader.readLine())!=null){
                builder.append(line);//每读一行都加到builder中
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
           if (reader!=null){
               try {
                   reader.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
            if (conn!=null){
                conn.disconnect();
            }
        }
        return builder.toString();

    }

    @Override
    protected void onPostExecute(String s) {//s为doInBackground传回的结果
        super.onPostExecute(s);
        if(callBack!=null){
//            callBack.getDate(s);
            callBack.getDate(s);
        }
    }


    public interface CallBack{//自定义接口
        void getDate(String result);//String result与第三个泛型类型相同
    }
}
