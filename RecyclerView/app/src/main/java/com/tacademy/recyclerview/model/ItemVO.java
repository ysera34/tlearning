package com.tacademy.recyclerview.model;

import com.tacademy.recyclerview.R;

/**
 * Created by yoon on 2016. 10. 31..
 */
public class ItemVO {

    private int imageId;
    private String name;

    private String[] nameArr = new String[] {
            "boram", "eunjung", "hyomin", "jiyeon", "qri", "soyeon"
    };

    private int[] imageArr = new int[] {
            R.drawable.t_ara_icon_boram,
            R.drawable.t_ara_icon_eunjung,
            R.drawable.t_ara_icon_hyomin,
            R.drawable.t_ara_icon_jiyeon,
            R.drawable.t_ara_icon_qri,
            R.drawable.t_ara_icon_soyeon
    };

    public ItemVO(int i) {
        this.imageId = imageArr[i % imageArr.length];
        this.name = nameArr[i % nameArr.length];
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
