package com.tacademy.action01;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by yoon on 2016. 11. 22..
 */

public class Item implements Serializable {

    private UUID mId;
    private String title;
    private String subTitle;

    public Item() {
        mId = UUID.randomUUID();
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
