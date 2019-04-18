package com.example.beijingnews.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/18 0018
 * Time: 17:10
 * Describe: ${as}
 */
public abstract class BaseFragment extends Fragment {
    protected View view;
    Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = getContextView(inflater,container);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @NonNull
    protected TextView getTextView(){
        TextView textView = new TextView(getActivity());
        textView.setText(this.getClass().getSimpleName());
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
        return textView;
    }
    public abstract View getContextView(LayoutInflater inflater,ViewGroup container);
}
