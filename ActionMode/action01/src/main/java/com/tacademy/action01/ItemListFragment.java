package com.tacademy.action01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yoon on 2016. 11. 22..
 */

public class ItemListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ItemAdapter mItemAdapter;
    private ArrayList<Item> mItems;

    private ActionMode mActionMode;

    public static ItemListFragment newInstance() {

        Bundle args = new Bundle();

        ItemListFragment fragment = new ItemListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ItemListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            mItems.add(new Item("Title :" + i, ", SubTitle : " + "sub" + i));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.fragment_item_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mItemAdapter = new ItemAdapter(mItems);
        mRecyclerView.setAdapter(mItemAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        private ArrayList<Item> mItems;

        public ItemAdapter(ArrayList<Item> items) {
            mItems = items;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_item, parent, false);
            return new ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Item item = mItems.get(position);
            holder.bindItem(item);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        private Item mItem;

        private TextView mTitleTextView;
        private TextView mSubTitleTextView;

        public ItemHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.title);
            mSubTitleTextView = (TextView) itemView.findViewById(R.id.sub_title);
        }

        public void bindItem(Item item) {
            mItem = item;
            mTitleTextView.setText(mItem.getTitle());
            mSubTitleTextView.setText(mItem.getSubTitle());
        }
    }

    class ActionModeCallback implements ActionMode.Callback {

        private boolean isListViewFragment;

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    }
}
