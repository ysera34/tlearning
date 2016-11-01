package com.tacademy.multitype.model;

/**
 * Created by yoon on 2016. 11. 1..
 */
public class ItemVO {

    private String text;

    public ItemVO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
