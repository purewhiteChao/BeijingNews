package com.example.beijingnews.welcome;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.beijingnews.Guide.GuideActivity;
import com.example.beijingnews.R;
import com.example.beijingnews.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    private ImageView imageview_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();

        AnimatorSet set = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageview_welcome,"scaleX",0,1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageview_welcome,"scaleY",0,1);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(imageview_welcome,"rotation",0,1080, Animation.RELATIVE_TO_SELF,0.5f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageview_welcome,"alpha",0,1);
        set.setDuration(5000);
        set.playTogether(scaleX,scaleY,rotate,alpha);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        imageview_welcome = (ImageView) findViewById(R.id.imageview_welcome);
    }
}
