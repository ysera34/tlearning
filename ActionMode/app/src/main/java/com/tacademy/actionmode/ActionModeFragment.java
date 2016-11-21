package com.tacademy.actionmode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by yoon on 2016. 11. 21..
 */

public class ActionModeFragment extends Fragment implements View.OnClickListener {

    private ArrayList<TestModel> mModels;
    private RecyclerView mTestRecyclerView;
    private ActionModeAdapter mActionModeAdapter;

    public static Fragment newInstance() {

        ActionModeFragment fragment = new ActionModeFragment();
        return fragment;
    }

    public ActionModeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModels = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestModel testModel = new TestModel();
            testModel.setName("hello" + i);
            testModel.setAge(i + 10);
            Log.d("Make Model", testModel.getName() + " " + testModel.getAge());
            mModels.add(testModel);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.fragment_action_mode, container, false);
        (view.findViewById(R.id.action_mode_button)).setOnClickListener(this);

        mTestRecyclerView = (RecyclerView) view.findViewById(R.id.action_mode_recycler_view);
        mTestRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActionModeAdapter = new ActionModeAdapter(mModels);
        mTestRecyclerView.setAdapter(mActionModeAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_mode_button :
                Toast.makeText(getActivity(), "Action Mode Button", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private class ActionModeAdapter extends RecyclerView.Adapter<ActionModeHolder> {

        private ArrayList<TestModel> mTestModels;

        public ActionModeAdapter(ArrayList<TestModel> testModels) {
            mTestModels = testModels;
        }

        @Override
        public ActionModeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_item_action_mode, parent, false);
            return new ActionModeHolder(view);
        }

        @Override
        public void onBindViewHolder(ActionModeHolder holder, int position) {
            TestModel testModel = mTestModels.get(position);
            holder.bindTestModel(testModel);
        }

        @Override
        public int getItemCount() {
            return mTestModels.size();
        }
    }

    private class ActionModeHolder extends RecyclerView.ViewHolder {

        private TestModel mTestModel;
        private TextView mNameTextView;
        private TextView mAgeTextView;

        public ActionModeHolder(View itemView) {
            super(itemView);

            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_name_text);
            mAgeTextView = (TextView) itemView.findViewById(R.id.list_item_age_text);
        }

        public void bindTestModel(TestModel testModel) {
            mTestModel = testModel;
            Log.d("bind Model", testModel.getName() + " " + testModel.getAge());
            mNameTextView.setText(mTestModel.getName());
            mAgeTextView.setText(String.valueOf(mTestModel.getAge()));
        }
    }

    private class TestModel {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
