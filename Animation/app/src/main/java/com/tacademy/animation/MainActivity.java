package com.tacademy.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private ImageView mImageView;
    private Button mButton1;

    Animation palpitateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.imageView);
        mButton1 = (Button) findViewById(R.id.button_1);
        mButton1.setOnClickListener(this);

        palpitateAnimation = AnimationUtils.loadAnimation(this, R.anim.palpitate);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_1 :
                mImageView.startAnimation(palpitateAnimation);
        }
    }
}
