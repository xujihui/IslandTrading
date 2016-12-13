package com.daomaidaomai.islandtrading.entity;

/**
 * Created by Administrator on 2016/12/13.
 */

public class Map {
    private int id;
    private double latitude;
    private double longitude;
    private int ImageId;

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

    public Map(int id, double latitude, double longitude, int imageId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        ImageId = imageId;
    }
}
