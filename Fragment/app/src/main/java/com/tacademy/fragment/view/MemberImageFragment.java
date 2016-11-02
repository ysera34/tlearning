package com.tacademy.fragment.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tacademy.fragment.R;
import com.tacademy.fragment.model.Member;
import com.tacademy.fragment.model.MemberStorage;

import java.util.UUID;

/**
 * Created by yoon on 2016. 11. 2..
 */
public class MemberImageFragment extends Fragment {

    private static final String ARG_MEMBER_ID = "member_id";
    private Member mMember;
    private ImageView mMemberImageView;

    public static MemberImageFragment newInstance(UUID memberId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MEMBER_ID, memberId);

        MemberImageFragment fragment = new MemberImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID memberId = (UUID) getArguments().getSerializable(ARG_MEMBER_ID);
        mMember = MemberStorage.get().getMember(memberId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_member_image, container, false);

        mMemberImageView = (ImageView) view.findViewById(R.id.member_image_view);
        mMemberImageView.setImageResource(mMember.getImageId());

        return view;
    }
}
