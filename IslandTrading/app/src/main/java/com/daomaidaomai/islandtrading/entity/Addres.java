package com.daomaidaomai.islandtrading.entity;

/**
 * Created by Administrator on 2016/11/20.
 */

public class Addres {
    private long id;
    private String sName;
    private String sNumber;
    private String sAddres;
    private String sStyle;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsNumber() {
        return sNumber;
    }

    public void setsNumber(String sNumber) {
        this.sNumber = sNumber;
    }

    public String getsAddres() {
        return sAddres;
    }

    public void setsAddres(String sAddres) {
        this.sAddres = sAddres;
    }

    public String getsStyle() {
        return sStyle;
    }

    public void setsStyle(String sStyle) {
        this.sStyle = sStyle;
    }

    public Addres(long id, String sName, String sNumber, String sAddres, String sStyle) {
        this.id = id;
        this.sName = sName;
        this.sNumber = sNumber;
        this.sAddres = sAddres;
        this.sStyle = sStyle;
    }

}
