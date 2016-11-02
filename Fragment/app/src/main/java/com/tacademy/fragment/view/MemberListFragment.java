package com.tacademy.fragment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.fragment.R;
import com.tacademy.fragment.adapter.MemberAdapter;
import com.tacademy.fragment.model.Member;
import com.tacademy.fragment.model.MemberStorage;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by yoon on 2016. 11. 2..
 */
public class MemberListFragment extends Fragment {

    private RecyclerView mMemberRecyclerView;
    private MemberAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_member_list, container, false);
        mMemberRecyclerView = (RecyclerView) view.findViewById(R.id.member_recycler_view);
        mMemberRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MemberStorage memberStorage = MemberStorage.get();
        HashMap<UUID, Member> memberHashMap = memberStorage.getMembers();

        if (mAdapter == null) {
            mAdapter = new MemberAdapter(memberHashMap);
            mMemberRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

        return view;
    }
}
