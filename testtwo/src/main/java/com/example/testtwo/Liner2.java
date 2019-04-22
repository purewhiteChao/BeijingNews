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
 * Time: 15:31
 * Describe: ${as}
 */
public class Liner2 extends LinearLayout {
    public Liner2(Context context) {
        super(context);
    }

    public Liner2(Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Liner2(Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("GC","Liner2:dispatch");

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("GC","Liner2:onTouchEvent");

        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("GC","Liner2:onIntercept");

        return false;
    }
}
