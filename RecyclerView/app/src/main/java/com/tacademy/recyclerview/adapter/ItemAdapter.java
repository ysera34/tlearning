package com.tacademy.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.recyclerview.R;
import com.tacademy.recyclerview.model.ItemVO;

import java.util.ArrayList;

/**
 * Created by yoon on 2016. 10. 31..
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    public class ItemHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;
        ItemVO mItemVO;

        public ItemHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_image);
            mTextView = (TextView) itemView.findViewById(R.id.item_title);
            mImageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    Toast.makeText(view.getContext(), mItemVO.getName(),
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }

        public void setItemVO(ItemVO itemVO) {
            mItemVO = itemVO;
            mImageView.setImageResource(mItemVO.getImageId());
            mTextView.setText(mItemVO.getName());
        }

    }

    ArrayList<ItemVO> mItemVOs;

    public ItemAdapter(ArrayList<ItemVO> itemVOs) {
        mItemVOs = itemVOs;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.setItemVO(mItemVOs.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemVOs.size();
    }
}
