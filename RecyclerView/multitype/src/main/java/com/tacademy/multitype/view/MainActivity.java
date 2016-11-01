package com.tacademy.multitype.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tacademy.multitype.R;
import com.tacademy.multitype.adapter.ItemAdapter;
import com.tacademy.multitype.model.ItemVO;

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

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());

        mRecyclerView.setLayoutManager(layoutManager);

        mItemVOs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mItemVOs.add(new ItemVO("##"+i));
        }

        mItemAdapter = new ItemAdapter(mItemVOs);
        mRecyclerView.setAdapter(mItemAdapter);
    }
}
