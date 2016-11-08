package com.tacademy.work.ycyoon_melonapi.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by yoon on 2016. 11. 8..
 */

public class SongStorage {

    private static SongStorage sSongStorage;

    private ArrayList<Song> mSongs;

    public static SongStorage get() {
        if (sSongStorage == null) {
            sSongStorage = new SongStorage();
        }
        return sSongStorage;
    }

    public SongStorage() {
        mSongs = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            Song song = new Song();
            song.setCurrentRank(i);
            song.setSongName("songName : " + i);
            song.setArtistName("artistName : " + i);
            song.setAlbumName("albumName : " + i);
            mSongs.add(song);
        }
    }

    public ArrayList<Song> getSongs() {
        return mSongs;
    }

    public Song getSong(UUID id) {
        for (Song song : mSongs) {
            if (song.getId().equals(id)) {
                return song;
            }
        }
        return null;
    }

    public Song getSong(int songId) {
        for (Song song : mSongs) {
            if (song.getSongId() == songId) {
                return song;
            }
        }
        return null;
    }
}
