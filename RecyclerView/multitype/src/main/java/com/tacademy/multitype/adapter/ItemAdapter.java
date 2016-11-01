package com.tacademy.multitype.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.multitype.R;
import com.tacademy.multitype.model.ItemVO;

import java.util.ArrayList;

/**
 * Created by yoon on 2016. 11. 1..
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int LEFT_VIEW_TYPE = 1;
    public static final int CENTER_VIEW_TYPE = 2;
    public static final int RIGHT_VIEW_TYPE = 3;
    int type;
    ArrayList<ItemVO> mItemVOs;

    public ItemAdapter(ArrayList<ItemVO> itemVOs) {
        mItemVOs = itemVOs;
    }

    public class LeftViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTextView;
        private ItemVO mItemVO;

        public LeftViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.list_item_text_view);
            itemView.setOnClickListener(this);
        }

        public void setTextView(ItemVO itemVO) {
            mTextView.setText(itemVO.getText());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),
                    "Left ViewHolder" + mItemVO.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public class CenterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTextView;
        private ItemVO mItemVO;

        public CenterViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.list_item_text_view);
            itemView.setOnClickListener(this);
        }

        public void setTextView(ItemVO itemVO) {
            mTextView.setText(itemVO.getText());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),
                    "Center ViewHolder" + mItemVO.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTextView;
        private ItemVO mItemVO;

        public RightViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.list_item_text_view);
            itemView.setOnClickListener(this);
        }

        public void setTextView(ItemVO itemVO) {
            mTextView.setText(itemVO.getText());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),
                    "Right ViewHolder" + mItemVO.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        return position % 3 + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case LEFT_VIEW_TYPE :
                view = inflater.inflate(R.layout.list_left_item, parent, false);
                return new LeftViewHolder(view);
            case CENTER_VIEW_TYPE :
                view = inflater.inflate(R.layout.list_center_item, parent, false);
                return new CenterViewHolder(view);
            case RIGHT_VIEW_TYPE :
                view = inflater.inflate(R.layout.list_right_item, parent, false);
                return new RightViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case LEFT_VIEW_TYPE :
                ((LeftViewHolder) holder).setTextView(mItemVOs.get(position));
                break;
            case CENTER_VIEW_TYPE :
                ((CenterViewHolder) holder).setTextView(mItemVOs.get(position));
                break;
            case RIGHT_VIEW_TYPE :
                ((RightViewHolder) holder).setTextView(mItemVOs.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mItemVOs.size();
    }
}
