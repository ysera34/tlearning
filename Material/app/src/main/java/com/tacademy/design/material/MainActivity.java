package com.tacademy.design.material;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    TabViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        viewPagerAdapter = new TabViewPagerAdapter(
                getSupportFragmentManager());
        setupTabViewPager();
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void setupTabViewPager() {
        viewPagerAdapter.addTabFragment(new Tab1Fragment(), "title1");
        viewPagerAdapter.addTabFragment(new Tab2Fragment(), "title2");
        viewPagerAdapter.addTabFragment(new Tab3Fragment(), "title3");

    }

    public class TabViewPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<String> tabTitles = new ArrayList<>();
        private ArrayList<Fragment> tabFragments = new ArrayList<>();

        public TabViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return super.getPageTitle(position);
            return tabTitles.get(position);
        }

        public void addTabFragment(Fragment fragment, String title) {
            tabFragments.add(fragment);
            tabTitles.add(title);
        }
    }
}
