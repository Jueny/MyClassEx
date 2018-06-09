package com.example.ex7_1.task;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/17.
 * 根据地址下载JSON格式的数据，返回一个字符串
 */

public class JSONTask extends AsyncTask<String,Void,String>{
    CallBack callBack;

    public JSONTask(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... params) {
//        StringBuilder result=new StringBuilder();
//        HttpURLConnection connection=null;
//        BufferedReader reader=null;
//        try {
//            URL url=new URL(params[0]);
//            connection= (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setReadTimeout(8000);
//            connection.setConnectTimeout(8000);
//            InputStream in=connection.getInputStream();
//            reader=new BufferedReader(new InputStreamReader(in));
//            String line;
//            while((line=reader.readLine())!=null){
//                result.append(line);
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if(reader!=null){
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(connection!=null){
//                connection.disconnect();
//            }
//        }
        String result=null;
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(params[0]).build();
        try {
            Response response=client.newCall(request).execute();
            result=response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(callBack!=null){
            callBack.getData(result);
        }
    }

    public interface CallBack{
        void getData(String result);
    }
}
