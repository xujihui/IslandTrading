package com.daomaidaomai.islandtrading.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/13.
 */

public class MapST implements Serializable {
    private int id;
    private double latitude;
    private double longitude;
    private int ImageId;
    private String mName;
    private String mContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
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

    public MapST(int id, double latitude, double longitude, int imageId, String mName, String mContent) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        ImageId = imageId;
        this.mName = mName;
        this.mContent = mContent;
    }
}
