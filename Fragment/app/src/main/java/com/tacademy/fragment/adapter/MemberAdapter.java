package com.tacademy.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.fragment.R;
import com.tacademy.fragment.model.Member;
import com.tacademy.fragment.view.MemberActivity;
import com.tacademy.fragment.view.MemberImageFragment;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * Created by yoon on 2016. 11. 1..
 */
public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder> {

    public class MemberHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mNameTextView;
        private Member mMember;

        public MemberHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_member_name);
            itemView.setOnClickListener(this);
        }

        public void setItemView(Member member) {
            this.mMember = member;
            mNameTextView.setText(mMember.getName());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "## " + mMember.getName() + " ##",
                    Toast.LENGTH_SHORT).show();
            FragmentManager fragmentManager =
                    ((MemberActivity) view.getContext()).getSupportFragmentManager();
//            Fragment fragmentMemberImage =
//                    fragmentManager.findFragmentById(R.id.fragment_image_container);
//
//            if (fragmentMemberImage == null) {
//                fragmentMemberImage = MemberImageFragment.newInstance(mMember.getId());
//                fragmentManager.beginTransaction()
//                        .add(R.id.fragment_image_container, fragmentMemberImage)
//                        .commit();
//            }

            Fragment fragmentMemberImage = MemberImageFragment.newInstance(mMember.getId());
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_image_container, fragmentMemberImage)
                    .commit();

        }
    }

    HashMap<UUID, Member> mMemberHashMap;

    public MemberAdapter(HashMap<UUID, Member> memberHashMap) {
        mMemberHashMap = memberHashMap;
    }

    @Override
    public MemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_member, parent, false);
        return new MemberHolder(view);
    }

    @Override
    public void onBindViewHolder(MemberHolder holder, int position) {
        Set<UUID> keys = mMemberHashMap.keySet();
        UUID[] keyArray = keys.toArray(new UUID[keys.size()]);
        Member member = mMemberHashMap.get(keyArray[position]);
        holder.setItemView(member);
    }

    @Override
    public int getItemCount() {
        return mMemberHashMap.size();
    }
}
