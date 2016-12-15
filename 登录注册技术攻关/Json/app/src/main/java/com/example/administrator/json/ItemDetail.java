package com.example.administrator.json;

import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2016/11/19.
 */
public class ItemDetail {
    private int Id;
    private String mName;
    private String mContent;
    private String mPlace;
    private String mOgnizition;
    private String time;
    //Date PRODUCT_TIME = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(str_time);


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

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmPlace() {
        return mPlace;
    }

    public void setmPlace(String mPlace) {
        this.mPlace = mPlace;
    }

    public String getmOgnizition() {
        return mOgnizition;
    }

    public void setmOgnizition(String mOgnizition) {
        this.mOgnizition = mOgnizition;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ItemDetail(int id, String mName, String mContent, String mPlace, String mOgnizition, String time) {
        Id = id;
        this.mName = mName;
        this.mContent = mContent;
        this.mPlace = mPlace;
        this.mOgnizition = mOgnizition;
        this.time = time;
    }
        @Override
    public String toString() {
        return "ItemDetail{" +
                "Id=" + Id +
                ", mName='" + mName + '\'' +
                ", mPlace=" + mPlace +
                ",mtime="+time+
                ", mContent='" + mContent + '\'' +
                ", mOgnizition" + mOgnizition +
                '}';
    }
}
