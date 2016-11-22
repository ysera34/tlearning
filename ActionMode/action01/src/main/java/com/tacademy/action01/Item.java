package com.tacademy.action01;

import java.io.Serializable;

/**
 * Created by yoon on 2016. 11. 22..
 */

public class Item implements Serializable {

    private String title;
    private String subTitle;

    public Item(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
