package com.tacademy.fragment.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tacademy.fragment.R;

public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentMemberList =
                fragmentManager.findFragmentById(R.id.fragment_member_list_container);

        if (fragmentMemberList == null) {
            fragmentMemberList = new MemberListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_member_list_container, fragmentMemberList)
                    .commit();
        }

    }
}
