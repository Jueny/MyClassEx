package com.example.ex7_1.bean;

/**
 * Created by Administrator on 2018/4/17.
 * 数据模型可以根据列表项控件的显示数据而定，也可以根据JSON数据内容而定
 */

public class News {
    //图片在网上地址，所以是字符串
    String imgUrl;
    String newsTitle;
    String newsContent;
    String newsData;
    public News(){}

    public News(String imgUrl, String newsContent, String newsData, String newsTitle) {
        this.imgUrl = imgUrl;
        this.newsContent = newsContent;
        this.newsData = newsData;
        this.newsTitle = newsTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsData() {
        return newsData;
    }

    public void setNewsData(String newsData) {
        this.newsData = newsData;
    }
}
