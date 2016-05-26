package com.cmcc.hyapps.KunlunTravel.designMode.observer;

/**
 * Created by gaoruishan on 16/4/4.
 */
public class News {
    private String title;
    private String body;

    public News(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
