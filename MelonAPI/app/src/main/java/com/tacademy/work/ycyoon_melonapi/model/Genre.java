package com.tacademy.work.ycyoon_melonapi.model;

/**
 * Created by yoon on 2016. 11. 8..
 */

public enum Genre {
    ALL("DP0000"), KPOP("DP0100"), POP("DP0200"), OST("DP0300"), JPOP("DP0400"),
    CLASSIC("DP0500"), CCM("DP0600"), KIDS("DP0700"), NEWAGE("DP0800"), JAZZ("DP0900"),
    WORLD("DP1000"), FAITH("DP1100"), TRADITIONAL("DP1200"), CHINESE("DP1300");

    private String genreId;

    private Genre(String genreId) {
        this.genreId = genreId;
    }
}
