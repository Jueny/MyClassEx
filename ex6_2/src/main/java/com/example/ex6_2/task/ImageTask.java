package com.example.ex6_2.task;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/10.
 */

public class ImageTask extends AsyncTask<String,Void,Bitmap> {
    public ImageTask(CallBack callBack) {
        this.callBack = callBack;
    }

    CallBack callBack;
    @Override
    protected Bitmap doInBackground(String... params) {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(params[0]).build();
        Bitmap result=null;
        try {
            Response response=client.newCall(request).execute();
            byte[] data=response.body().bytes();
            result= BitmapFactory.decodeByteArray(data,0,data.length);//(数据，数据起始位置，数据结束位置)
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (callBack!=null){
            callBack.getDate(bitmap);
        }
    }

    public interface CallBack{//自定义接口
        void getDate(Bitmap result);//String result与第三个泛型类型相同
    }
}
