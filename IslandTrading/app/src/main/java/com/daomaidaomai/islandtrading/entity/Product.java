package com.daomaidaomai.islandtrading.entity;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class Product {
    private int id; //设置id是为了标识每一个Item（即每一行）
    private String mImage; //图片
    private String mTitle;  //标题
    private Double mPrice;  //价格
    private String mContent; //内容
    private String mTime;  //展示时间

    public Product(int id, String mImage, String mTitle, Double mPrice, String mContent, String mTime) {
        this.id = id;
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mPrice = mPrice;
        this.mContent = mContent;
        this.mTime = mTime;
    }

    public Product(int id, String mImage, String mTitle, double mPrice, String mContent) {
        this.id = id;
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mPrice = mPrice;
        this.mContent = mContent;
    }

    public Product(int id, String mImage, String mTitle, double mPrice) {
        this.id = id;
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mPrice = mPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
