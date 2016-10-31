package com.tacademy.recyclerview.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.tacademy.recyclerview.R;
import com.tacademy.recyclerview.adapter.ItemAdapter;
import com.tacademy.recyclerview.model.ItemVO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ItemAdapter mItemAdapter;
    ArrayList<ItemVO> mItemVOs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mItemVOs = new ArrayList<>();
        mItemAdapter = new ItemAdapter(mItemVOs);
        mRecyclerView.setAdapter(mItemAdapter);

        setArrayList();
    }

    private void setArrayList() {

        for (int i = 0; i < 50; i++) {
            mItemVOs.add(new ItemVO(i));
        }

    }
}
