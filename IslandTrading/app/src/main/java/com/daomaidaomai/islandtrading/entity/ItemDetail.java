package com.daomaidaomai.islandtrading.entity;

/**
 * Created by lenovo on 2016/11/19.
 */
public class ItemDetail {
    private int Id;
    private String mName;
    private String mPicture;
    private String mContent;
    private double mPrice;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPicture() {
        return mPicture;
    }

    public void setmPicture(String mPicture) {
        this.mPicture = mPicture;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public double getmPrice() {
        return mPrice;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public ItemDetail(int id, String mName, String mPicture, String mContent, double mPrice) {
        Id = id;
        this.mName = mName;
        this.mPicture = mPicture;
        this.mContent = mContent;
        this.mPrice = mPrice;
    }

    public ItemDetail() {
    }

    @Override
    public String toString() {
        return "ItemDetail{" +
                "Id=" + Id +
                ", mName='" + mName + '\'' +
                ", mPicture=" + mPicture +
                ", mContent='" + mContent + '\'' +
                ", mPrice=" + mPrice +
                '}';
    }
}
