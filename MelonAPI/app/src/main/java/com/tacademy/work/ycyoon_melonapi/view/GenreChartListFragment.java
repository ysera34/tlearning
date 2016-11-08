package com.tacademy.work.ycyoon_melonapi.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tacademy.work.ycyoon_melonapi.R;
import com.tacademy.work.ycyoon_melonapi.model.Song;
import com.tacademy.work.ycyoon_melonapi.model.SongStorage;

import java.util.ArrayList;

/**
 * Created by yoon on 2016. 11. 8..
 */
public class GenreChartListFragment extends Fragment {

    private RecyclerView mGenreChartRecyclerView;
    private GenreChartAdapter mGenreChartAdapter;

    public GenreChartListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_chart_list, container, false);

        mGenreChartRecyclerView = (RecyclerView) view.findViewById(R.id.list_chart_recycler_view);
        mGenreChartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SongStorage songStorage = SongStorage.get();
        ArrayList<Song> songs = songStorage.getSongs();

        if (mGenreChartAdapter == null) {
            mGenreChartAdapter = new GenreChartAdapter(songs);
            mGenreChartRecyclerView.setAdapter(mGenreChartAdapter);
        } else {
            mGenreChartAdapter.notifyDataSetChanged();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private class GenreChartAdapter extends RecyclerView.Adapter<GenreChartHolder> {

        private ArrayList<Song> mSongs;

        public GenreChartAdapter(ArrayList<Song> songs) {
            mSongs = songs;
        }

        @Override
        public GenreChartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_item_genre, parent, false);
            return new GenreChartHolder(view);
        }

        @Override
        public void onBindViewHolder(GenreChartHolder holder, int position) {
            Song song = mSongs.get(position);
            holder.bindSong(song);
        }

        @Override
        public int getItemCount() {
            return mSongs.size();
        }
    }

    private class GenreChartHolder extends RecyclerView.ViewHolder {

        private Song mSong;
        private TextView mSongNameTextView;

        public GenreChartHolder(View itemView) {
            super(itemView);
            mSongNameTextView = (TextView) itemView.findViewById(R.id.list_item_song_name);
        }
        public void bindSong(Song song) {
            mSong = song;
            mSongNameTextView.setText(mSong.getSongName());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_genre_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.genre_all :
                return true;
            case R.id.genre_kpop :
                return true;
            case R.id.genre_pop :
                return true;
            case R.id.genre_ost :
                return true;
            case R.id.genre_jpop :
                return true;
            case R.id.genre_classic :
                return true;
            case R.id.genre_ccm :
                return true;
            case R.id.genre_kids :
                return true;
            case R.id.genre_newage :
                return true;
            case R.id.genre_jazz :
                return true;
            case R.id.genre_world :
                return true;
            case R.id.genre_faith :
                return true;
            case R.id.genre_traditional :
                return true;
            case R.id.genre_chinese :
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
