package com.tacademy.work.ycyoon_melonapi.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by yoon on 2016. 11. 8..
 */

public class Song {

    private UUID mId;
    private int mSongId;
    private String mSongName;
    private String mArtistName;
    private String mAlbumName;
    private int currentRank;
    private Date mIssueDate;

    private String mGenre;

    public Song() {
        mId = UUID.randomUUID();
        mIssueDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public int getSongId() {
        return mSongId;
    }

    public void setSongId(int songId) {
        mSongId = songId;
    }

    public String getSongName() {
        return mSongName;
    }

    public void setSongName(String songName) {
        mSongName = songName;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public void setAlbumName(String albumName) {
        mAlbumName = albumName;
    }

    public int getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(int currentRank) {
        this.currentRank = currentRank;
    }

    public Date getIssueDate() {
        return mIssueDate;
    }

    public void setIssueDate(Date issueDate) {
        mIssueDate = issueDate;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }
}
