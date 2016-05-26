package com.cmcc.hyapps.KunlunTravel.designMode.factory;

/**
 * 模型
 * Created by gaoruishan on 16/5/13.
 */
public class Video {
    private String name;
    private String url;
    private int time;

    public Video(String name, String url, int time) {
        this.name = name;
        this.url = url;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
