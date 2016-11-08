package com.tacademy.work.ycyoon_melonapi.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
public class PopChartListFragment extends Fragment {

    private RecyclerView mPopChartRecyclerView;
    private PopChartAdapter mPopChartAdapter;

    public PopChartListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_chart_list, container, false);

        mPopChartRecyclerView = (RecyclerView) view.findViewById(R.id.list_chart_recycler_view);
        mPopChartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SongStorage songStorage = SongStorage.get();
        ArrayList<Song> songs = songStorage.getSongs();

        if (mPopChartAdapter == null) {
            mPopChartAdapter = new PopChartAdapter(songs);
            mPopChartRecyclerView.setAdapter(mPopChartAdapter);
        } else {
            mPopChartAdapter.notifyDataSetChanged();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private class PopChartAdapter extends RecyclerView.Adapter<PopChartHolder> {

        private ArrayList<Song> mSongs;

        public PopChartAdapter(ArrayList<Song> songs) {
            mSongs = songs;
        }

        @Override
        public PopChartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_item_pop, parent, false);
            return new PopChartHolder(view);
        }

        @Override
        public void onBindViewHolder(PopChartHolder holder, int position) {
            Song song = mSongs.get(position);
            holder.bindSong(song);
        }

        @Override
        public int getItemCount() {
            return mSongs.size();
        }
    }

    private class PopChartHolder extends RecyclerView.ViewHolder {

        private Song mSong;
        private TextView mSongNameTextView;

        public PopChartHolder(View itemView) {
            super(itemView);
            mSongNameTextView = (TextView) itemView.findViewById(R.id.list_item_song_name);
        }

        public void bindSong(Song song) {
            mSong = song;
            mSongNameTextView.setText(mSong.getSongName());
        }
    }
}
