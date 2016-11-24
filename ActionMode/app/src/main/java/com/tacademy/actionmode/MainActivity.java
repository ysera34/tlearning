package com.tacademy.actionmode;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment mFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
//        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Fragment
        FragmentManager fm = getSupportFragmentManager();
        mFragmentContainer = fm.findFragmentById(R.id.fragment_container);
        if (mFragmentContainer == null) {
            mFragmentContainer = ActionModeFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.fragment_container, mFragmentContainer)
                    .commit();
        }


    /*
        startSupportActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                // Inflate our menu from a resource file
                actionMode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);

                // Return true so that the action mode is shown
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                // As we do not need to modify the menu before displayed, we return false.
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                // Similar to menu handling in Activity.onOptionsItemSelected()
                switch (menuItem.getItemId()) {
                    case R.id.action_mode_settings1:
                        // Some remove functionality
                        Toast.makeText(getApplicationContext(), "hello action mode", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                // Allows you to be notified when the action mode is dismissed
            }
        });
        */
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Toast.makeText(getApplicationContext(), "액티비티에서 눌렸어요.", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
