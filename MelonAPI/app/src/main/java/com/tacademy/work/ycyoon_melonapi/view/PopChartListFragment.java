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
public class PopChartListFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mPopChartRecyclerView;
    private PopChartAdapter mPopChartAdapter;

    private ArrayList<Song> mSongs;
    private ProgressDialog mProgressDialog;

    public PopChartListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSongs = new ArrayList<>();
        new PopChartTask().execute(BaseConnection.URL_CHARTS_REALTIME, "20", "1");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_chart_list, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mPopChartRecyclerView = (RecyclerView) view.findViewById(R.id.list_chart_recycler_view);
        mPopChartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_progress, null));
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                new PopChartTask().execute(BaseConnection.URL_CHARTS_REALTIME, "20", "1");
            }
        });

//        SongStorage songStorage = SongStorage.get();
//        ArrayList<Song> songs = songStorage.getSongs();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onRefresh() {
        new PopChartTask().execute(BaseConnection.URL_CHARTS_REALTIME, "20", "1");
    }

    private void updateUI(ArrayList<Song> songs) {
        mPopChartAdapter = new PopChartAdapter(songs);
        mPopChartRecyclerView.setAdapter(mPopChartAdapter);
//        if (mPopChartAdapter == null) {
//            mPopChartAdapter = new PopChartAdapter(mSongs);
//            mPopChartRecyclerView.setAdapter(mPopChartAdapter);
//        } else {
//            mPopChartAdapter.notifyDataSetChanged();
//        }
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

    private class PopChartHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Song mSong;
        private TextView mCurrentRankTextView;
        private TextView mSongNameTextView;
        private TextView mArtistNameTextView;
        private TextView mAlbumNameTextView;

        public PopChartHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mCurrentRankTextView = (TextView) itemView.findViewById(R.id.list_item_current_rank);
            mSongNameTextView = (TextView) itemView.findViewById(R.id.list_item_song_name);
            mArtistNameTextView = (TextView) itemView.findViewById(R.id.list_item_artist_name);
            mAlbumNameTextView = (TextView) itemView.findViewById(R.id.list_item_album_name);
        }

        public void bindSong(Song song) {
            mSong = song;
            mCurrentRankTextView.setText(String.valueOf(mSong.getCurrentRank()));
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

    private class PopChartTask extends AsyncTask<String, Void, ArrayList<Song>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("Please wait.....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected ArrayList<Song> doInBackground(String... strings) {
            int count = Integer.parseInt(strings[1]);
            int page = Integer.parseInt(strings[2]);

            return BaseConnection.requestChartsRealtime(
                    BaseConnection.URL_CHARTS_REALTIME, count, page);
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
