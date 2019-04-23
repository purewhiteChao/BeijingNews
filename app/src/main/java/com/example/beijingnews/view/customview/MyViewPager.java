package com.example.beijingnews.view.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/22 0022
 * Time: 19:28
 * Describe: ${as}
 */
public class MyViewPager extends ViewPager {

    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;
        if(heightMode == MeasureSpec.AT_MOST){
            int childCount = this.getChildCount();
            for(int i=0;i<childCount;i++){
                View childAt = this.getChildAt(i);
//                int childSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
//                childAt.measure(widthMeasureSpec,childSpec);
                if(childAt.getMeasuredHeight()>height){
                    height = childAt.getMeasuredHeight();
                }
            }
            setMeasuredDimension(widthSize,height);
        }else{
            setMeasuredDimension(widthSize,heightSize);
        }


    }
}
