package com.tacademy.work.ycyoon_melonapi.view;

import android.app.ProgressDialog;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
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
import android.widget.Toast;

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
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_progress, null));
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

    private class GenreChartHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Song mSong;
        private TextView mCurrentRankTextView;
        private TextView mGenreTextView;
        private TextView mSongNameTextView;
        private TextView mArtistNameTextView;
        private TextView mAlbumNameTextView;

        public GenreChartHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mCurrentRankTextView = (TextView) itemView.findViewById(R.id.list_item_current_rank);
            mGenreTextView = (TextView) itemView.findViewById(R.id.list_item_genre);
            mSongNameTextView = (TextView) itemView.findViewById(R.id.list_item_song_name);
            mArtistNameTextView = (TextView) itemView.findViewById(R.id.list_item_artist_name);
            mAlbumNameTextView = (TextView) itemView.findViewById(R.id.list_item_album_name);
        }

        public void bindSong(Song song) {
            mSong = song;
            mCurrentRankTextView.setText(String.valueOf(mSong.getCurrentRank()));
            mGenreTextView.setText(mSong.getGenre());
            mSongNameTextView.setText(mSong.getSongName());
            mSongNameTextView.setSelected(true);
            mArtistNameTextView.setText(mSong.getArtistName());
            mAlbumNameTextView.setText(mSong.getAlbumName());
        }

        @Override
        public void onClick(View view) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                SimpleDateFormat transFormat = new SimpleDateFormat(("yyyyMMHH"));
                String issueDate = transFormat.format(mSong.getIssueDate());

                String songIssueMessage =
                        getString(R.string.song_issue_date_toast, mSong.getSongName(), issueDate);
                Toast.makeText(getActivity(), songIssueMessage, Toast.LENGTH_SHORT).show();
            } else {
                String songInfoMessage =
                        getString(R.string.song_info_toast, mSong.getSongName(), mSong.getArtistName());
                Toast.makeText(getActivity(), songInfoMessage, Toast.LENGTH_SHORT).show();
            }
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
                mGenreCode = "DP0000";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_kpop :
                mGenreCode = "DP0100";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_pop :
                mGenreCode = "DP0200";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_ost :
                mGenreCode = "DP0300";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_jpop :
                mGenreCode = "DP0400";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_classic :
                mGenreCode = "DP0500";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_ccm :
                mGenreCode = "DP0600";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_kids :
                mGenreCode = "DP0700";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_newage :
                mGenreCode = "DP0800";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_jazz :
                mGenreCode = "DP0900";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_world :
                mGenreCode = "DP1000";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_faith :
                mGenreCode = "DP1100";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_traditional :
                mGenreCode = "DP1200";
                refreshRequest(mGenreCode);
                return true;
            case R.id.genre_chinese :
                mGenreCode = "DP1300";
                refreshRequest(mGenreCode);
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
            if (songs.size() != 0) {
                mSongs = songs;
                updateUI(mSongs);
            } else {
                Toast.makeText(getActivity(),
                        getString(R.string.request_no_data), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
