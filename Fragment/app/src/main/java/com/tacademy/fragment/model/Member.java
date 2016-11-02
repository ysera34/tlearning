package com.tacademy.fragment.model;

import java.util.UUID;

/**
 * Created by yoon on 2016. 11. 1..
 */
public class Member {

    private UUID mId;
    private int imageId;
    private String name;

    public Member(int imageId, String name) {
        mId = UUID.randomUUID();
        this.imageId = imageId;
        this.name = name;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
