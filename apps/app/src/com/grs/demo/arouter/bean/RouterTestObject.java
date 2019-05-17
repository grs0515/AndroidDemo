package com.grs.demo.arouter.bean;

import java.io.Serializable;

/**
 * @author:gaoruishan
 * @date:2019/1/22/10:07
 * @email:grs0515@163.com
 */
public class RouterTestObject implements Serializable {
    private String name;
    private String nick;


    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick == null ? "" : nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
