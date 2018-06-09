package com.example.ex7_1.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by Administrator on 2018/4/17.
 * 根据地址下载一幅图片，返回的是Bitmap格式的数据
 */

public class ImageTask extends AsyncTask<String,Void,Bitmap>{
    CallBack callBack;

    public ImageTask(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(params[0]).build();
        Bitmap pic=null;
        try {
            Response response=client.newCall(request).execute();
            byte[] temp=response.body().bytes();
            pic= BitmapFactory.decodeByteArray(temp,0,temp.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pic;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(callBack!=null){
            callBack.getData(bitmap);
        }
    }

    public interface CallBack{
        void getData(Bitmap pic);
    }
}
