package com.example.testtwo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/19 0019
 * Time: 15:29
 * Describe: ${as}
 */
public class Liner1 extends LinearLayout {
    public Liner1(Context context) {
        super(context);
    }

    public Liner1(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Liner1(Context context,@Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("GC","Liner1:dispatch");

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("GC","Liner1:onTouchEvent");

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("GC","Liner1:onIntercept");

        return super.onInterceptTouchEvent(ev);
    }
}
