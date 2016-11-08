package com.tacademy.work.ycyoon_melonapi.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.work.ycyoon_melonapi.R;

import java.util.ArrayList;

import static com.tacademy.work.ycyoon_melonapi.R.id.toolbar;

/**
 * Created by yoon on 2016. 11. 8..
 */

public class ChartFragment extends Fragment {

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ArrayList<Fragment> mChartListFragments;
    private ArrayList<String> mChartListFragmentsTitles;

    public ChartFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mChartListFragments = new ArrayList<>();
        mChartListFragmentsTitles = new ArrayList<>();

        addChartFragment(new PopChartListFragment(), "pop");
        addChartFragment(new GenreChartListFragment(), "genre");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        mCollapsingToolbarLayout =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);

        mToolbar = (Toolbar) view.findViewById(toolbar);
        if (mToolbar != null) {
            ((FragmentActivity) container.getContext()).setSupportActionBar(mToolbar);
        }

        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);

        FragmentManager fm = getChildFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return mChartListFragments.get(position);
            }

            @Override
            public int getCount() {
                return mChartListFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
//                return super.getPageTitle(position);
                return mChartListFragmentsTitles.get(position);
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void addChartFragment(Fragment fragment, String title) {
        mChartListFragments.add(fragment);
        mChartListFragmentsTitles.add(title);
    }

}
