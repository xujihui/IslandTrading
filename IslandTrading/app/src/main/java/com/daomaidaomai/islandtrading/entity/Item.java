package com.daomaidaomai.islandtrading.entity;

/**
 * Created by lenovo on 2016/11/19.
 */
public class Item {
    private int Id;
    private String mName;
    private int mPicture;

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

    public int getmPicture() {
        return mPicture;
    }

    public void setmPicture(int mPicture) {
        this.mPicture = mPicture;
    }

    public Item(int id, String mName, int mPicture) {
        Id = id;
        this.mName = mName;
        this.mPicture = mPicture;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "Id=" + Id +
                ", mName='" + mName + '\'' +
                ", mPicture=" + mPicture +
                '}';
    }
}
