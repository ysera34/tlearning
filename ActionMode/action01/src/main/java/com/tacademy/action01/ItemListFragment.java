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
import android.widget.Toast;

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
            Item item = new Item();
            item.setTitle("title : " + i);
            item.setSubTitle("sub title : " + i);
            mItems.add(item);
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

    class ItemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private Item mItem;

        private TextView mTitleTextView;
        private TextView mSubTitleTextView;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.title);
            mSubTitleTextView = (TextView) itemView.findViewById(R.id.sub_title);
        }

        public void bindItem(Item item) {
            mItem = item;
            mTitleTextView.setText(mItem.getTitle());
            mSubTitleTextView.setText(mItem.getSubTitle());
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {

            return true;
        }
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        private boolean mActiveModeActive = false;

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            menu.findItem(R.id.action_copy).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.action_forward).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.findItem(R.id.action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete :
                    Toast.makeText(getActivity(), "action_delete", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_copy :
                    Toast.makeText(getActivity(), "action_copy", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_forward :
                    Toast.makeText(getActivity(), "action_forward", Toast.LENGTH_SHORT).show();
                    break;
            }

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };

}
