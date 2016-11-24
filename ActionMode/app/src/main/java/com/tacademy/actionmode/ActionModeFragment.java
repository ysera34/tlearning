package com.tacademy.actionmode;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

    private TextView mActionModeTestTextView;
    private boolean mActionModeActive = false;

    public static Fragment newInstance() {

        ActionModeFragment fragment = new ActionModeFragment();
        return fragment;
    }

    public ActionModeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
//        (view.findViewById(R.id.action_mode_button)).setOnClickListener(this);
        (view.findViewById(R.id.action_mode_button)).setVisibility(View.GONE);
        mActionModeTestTextView = (TextView) view.findViewById(R.id.action_mode_test_text_view);
        mActionModeTestTextView.setVisibility(View.GONE);
        mTestRecyclerView = (RecyclerView) view.findViewById(R.id.action_mode_recycler_view);
        mTestRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActionModeAdapter = new ActionModeAdapter(mModels);
        mTestRecyclerView.setAdapter(mActionModeAdapter);

        mActionModeTestTextView.setText("0");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_mode_button :
//                Toast.makeText(getActivity(), "Action Mode Button", Toast.LENGTH_SHORT).show();
//                if(!mActionModeActive) {
//                    getActivity().startActionMode(mActionModeCallback);
//                    mActionModeActive = true;
//                }
                break;
        }
    }

    //action mode (ActionMode.Callback implements)
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        int statusBarColor;

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getActivity().getMenuInflater().inflate(R.menu.menu_action_mode, menu);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                statusBarColor = getActivity().getWindow().getStatusBarColor();
                getActivity().getWindow().setStatusBarColor(0xFFEEEEEE);
            }
            mActionModeAdapter.notifyItemRangeChanged(0, mModels.size());
            mActionModeAdapter.notifyDataSetChanged();
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    Toast.makeText(getActivity(), "선택하신 항목이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getActivity().getWindow().setStatusBarColor(statusBarColor);
            }
            mActionModeActive = false;
            mActionModeAdapter.notifyItemRangeChanged(0, mModels.size());
            mActionModeAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getActivity(), "프레그먼트에서 눌렸어요.", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "Action Mode Button", Toast.LENGTH_SHORT).show();
            if(!mActionModeActive) {
                getActivity().startActionMode(mActionModeCallback);
                mActionModeActive = true;
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ActionModeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private ArrayList<TestModel> mTestModels;

        public ActionModeAdapter(ArrayList<TestModel> testModels) {
            mTestModels = testModels;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_item_action_mode, parent, false);
            if (mActionModeActive) {
//                return new ActionModeHolder(view);
                ActionModeHolder holder = new ActionModeHolder(view);
                holder.setIsRecyclable(false);
                return holder;
            } else {
//                return new StaticModeHolder(view);
                StaticModeHolder holder = new StaticModeHolder(view);
                holder.setIsRecyclable(false);
                return holder;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TestModel testModel = mTestModels.get(position);
            if (mActionModeActive && holder instanceof ActionModeHolder) {
                ((ActionModeHolder) holder).bindTestModel(testModel);
            } else if (!mActionModeActive && holder instanceof StaticModeHolder) {
                ((StaticModeHolder) holder).bindTestModel(testModel);
            }
        }

        @Override
        public int getItemCount() {
            return mTestModels.size();
        }
    }

    private class StaticModeHolder extends RecyclerView.ViewHolder {

        private TestModel mTestModel;
        private CheckBox mItemCheckBox;
        private TextView mNameTextView;
        private TextView mAgeTextView;

        public StaticModeHolder(View itemView) {
            super(itemView);

            mItemCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_check_box);
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

    private class ActionModeHolder extends RecyclerView.ViewHolder {

        private TestModel mTestModel;
        private CheckBox mItemCheckBox;
        private TextView mNameTextView;
        private TextView mAgeTextView;

        public ActionModeHolder(View itemView) {
            super(itemView);

            mItemCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_check_box);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_name_text);
            mAgeTextView = (TextView) itemView.findViewById(R.id.list_item_age_text);
        }

        public void bindTestModel(TestModel testModel) {
            mTestModel = testModel;
            Log.d("bind Model", testModel.getName() + " " + testModel.getAge());
            mNameTextView.setText(mTestModel.getName());
            mAgeTextView.setText(String.valueOf(mTestModel.getAge()));
            mItemCheckBox.setVisibility(View.VISIBLE);
//            mItemCheckBox.animate()
//                    .translationX(mItemCheckBox.getWidth())
//                    .setDuration(1000)
//                    .setListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            super.onAnimationEnd(animation);
//                            mItemCheckBox.setVisibility(View.VISIBLE);
//                        }
//                    });
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
