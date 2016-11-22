package com.tacademy.action01;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Fragment mContainerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        mContainerFragment = fm.findFragmentById(R.id.fragment_container);
        mContainerFragment = ItemListFragment.newInstance();
        fm.beginTransaction()
                .add(R.id.fragment_container, mContainerFragment)
                .commit();
    }
}
