package com.tacademy.work.ycyoon_melonapi.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.tacademy.work.ycyoon_melonapi.common.BaseConnection;
import com.tacademy.work.ycyoon_melonapi.model.Song;

import java.util.ArrayList;

/**
 * Created by yoon on 2016. 11. 8..
 */
public class GenreChartListFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mGenreChartRecyclerView;
    private GenreChartAdapter mGenreChartAdapter;

    private ArrayList<Song> mSongs;
    private ProgressDialog mProgressDialog;

    String mGenreCode = "DP0000";

    public GenreChartListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mSongs = new ArrayList<>();
        refreshRequest(mGenreCode);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_chart_list, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mGenreChartRecyclerView = (RecyclerView) view.findViewById(R.id.list_chart_recycler_view);
        mGenreChartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                refreshRequest(mGenreCode);
            }
        });

//        SongStorage songStorage = SongStorage.get();
//        ArrayList<Song> songs = songStorage.getSongs();

        return view;
    }

    @Override
    public void onRefresh() {
        refreshRequest(mGenreCode);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void refreshRequest(String genreCode) {
        new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, genreCode, "20", "1");
    }

    private void updateUI(ArrayList<Song> songs) {
        mGenreChartAdapter = new GenreChartAdapter(songs);
        mGenreChartRecyclerView.setAdapter(mGenreChartAdapter);
//        if (mGenreChartAdapter == null) {
//            mGenreChartAdapter = new GenreChartAdapter(songs);
//            mGenreChartRecyclerView.setAdapter(mGenreChartAdapter);
//        } else {
//            mGenreChartAdapter.notifyDataSetChanged();
//        }
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
        private TextView mCurrentRankTextView;
        private TextView mGenreTextView;
        private TextView mSongNameTextView;
        private TextView mArtistNameTextView;

        public GenreChartHolder(View itemView) {
            super(itemView);
            mCurrentRankTextView = (TextView) itemView.findViewById(R.id.list_item_current_rank);
            mGenreTextView = (TextView) itemView.findViewById(R.id.list_item_genre);
            mSongNameTextView = (TextView) itemView.findViewById(R.id.list_item_song_name);
            mArtistNameTextView = (TextView) itemView.findViewById(R.id.list_item_artist_name);
        }

        public void bindSong(Song song) {
            mSong = song;
            mCurrentRankTextView.setText(String.valueOf(mSong.getCurrentRank()));
            mGenreTextView.setText(mSong.getGenre());
            mSongNameTextView.setText(mSong.getSongName());
            mSongNameTextView.setSelected(true);
            mArtistNameTextView.setText(mSong.getArtistName());
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
            case R.id.genre_all ://DP0000
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP0000", "20", "1");
                mGenreCode = "DP0000";
                return true;
            case R.id.genre_kpop ://DP0100
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP0100", "20", "1");
                mGenreCode = "DP0100";
                return true;
            case R.id.genre_pop ://DP0200
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP0200", "20", "1");
                mGenreCode = "DP0200";
                return true;
            case R.id.genre_ost ://DP0300
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP0300", "20", "1");
                mGenreCode = "DP0300";
                return true;
            case R.id.genre_jpop ://DP0400
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP0400", "20", "1");
                mGenreCode = "DP0400";
                return true;
            case R.id.genre_classic ://DP0500
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP0500", "20", "1");
                mGenreCode = "DP0500";
                return true;
            case R.id.genre_ccm ://DP0600
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP0600", "20", "1");
                mGenreCode = "DP0600";
                return true;
            case R.id.genre_kids ://DP0700
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP0700", "20", "1");
                mGenreCode = "DP0700";
                return true;
            case R.id.genre_newage ://DP0800
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP0800", "20", "1");
                mGenreCode = "DP0800";
                return true;
            case R.id.genre_jazz ://DP0900
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP0900", "20", "1");
                mGenreCode = "DP0900";
                return true;
            case R.id.genre_world ://DP1000
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP1000", "20", "1");
                mGenreCode = "DP1000";
                return true;
            case R.id.genre_faith ://DP1100
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP1100", "20", "1");
                mGenreCode = "DP1100";
                return true;
            case R.id.genre_traditional ://DP1200
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP1200", "20", "1");
                mGenreCode = "DP1200";
                return true;
            case R.id.genre_chinese ://DP1300
                new GenreChartTask().execute(BaseConnection.URL_CHARTS_GENRE, "DP1300", "20", "1");
                mGenreCode = "DP1300";
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class GenreChartTask extends AsyncTask<String, Void, ArrayList<Song>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mSongs.clear();
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("Please wait.....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected ArrayList<Song> doInBackground(String... strings) {
            int count = Integer.parseInt(strings[2]);
            int page = Integer.parseInt(strings[3]);

            return BaseConnection.requestChartsGenre(
                    BaseConnection.URL_CHARTS_GENRE, strings[1], count, page);
        }

        @Override
        protected void onPostExecute(ArrayList<Song> songs) {
            super.onPostExecute(songs);
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            mSwipeRefreshLayout.setRefreshing(false);
            mSongs = songs;
            updateUI(mSongs);
        }
    }
}
