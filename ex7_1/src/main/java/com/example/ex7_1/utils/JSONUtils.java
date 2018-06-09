package com.example.ex7_1.utils;

import android.text.TextUtils;

import com.example.ex7_1.bean.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 * 解析JSON格式数据，传入字符串表示的JSON数据，得到List<News>类型的结果
 */

public class JSONUtils {
    public static List<News> parseJson(String data){
        List<News> result=new ArrayList<>();
        if(TextUtils.isEmpty(data)){
            return result;
        }
        try {
            JSONObject object1=new JSONObject(data);
            JSONObject object2=object1.getJSONObject("paramz");
            JSONArray array=object2.getJSONArray("feeds");
            for (int i=0;i<array.length();i++){
                JSONObject object3=array.getJSONObject(i);
                JSONObject object4=object3.getJSONObject("data");
                News news=new News();
                news.setNewsTitle(object4.getString("subject"));
                news.setNewsContent(object4.getString("summary"));
                news.setImgUrl(object4.getString("cover"));
                news.setNewsData(object4.getString("changed"));
                result.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
