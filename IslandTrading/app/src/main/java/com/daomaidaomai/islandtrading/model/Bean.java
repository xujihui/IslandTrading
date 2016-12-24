package com.daomaidaomai.islandtrading.model;

/**
 * bean 类
 * Created by yetwish on 2015-05-11
 */

public class Bean {
    private int id;
    private String iconId;
    private String title;
    private String content;
    private String comments;

    public Bean(int id, String iconId, String title, String content, String comments) {
        this.id = id;
        this.iconId = iconId;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
