package com.example.beijingnews.view.customview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beijingnews.R;

import org.w3c.dom.Text;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/24 0024
 * Time: 13:52
 * Describe: ${as}
 */
public class MyRecyclerView extends RecyclerView.Adapter {
    private RecyclerView.Adapter adapter;
    private final int ITEM_CODE = 0;
    private final int FOOTER_CODE = 1;
    private String footerStatus = "还没有滑动";
    public static final String LOADING = "正在加载";
    public static final String LOADED = "加载完成";
    public static final String LOAD_FAIL = "没有更多内容";

    public MyRecyclerView(RecyclerView.Adapter adapter){
        this.adapter = adapter;
    }

    public void setFooterStatus(String footerStatus){
        this.footerStatus = footerStatus;
        adapter.notifyDataSetChanged();
        this.notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i==FOOTER_CODE){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_footer,viewGroup,false);
            return new MyFooterViewHolder(view);
        }else{
            return adapter.onCreateViewHolder(viewGroup,i);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int itemViewType = getItemViewType(i);
        if(itemViewType == FOOTER_CODE){
            MyFooterViewHolder myFooterViewHolder = (MyFooterViewHolder) viewHolder;
            myFooterViewHolder.textView.setText(footerStatus);


        }else{
            adapter.onBindViewHolder(viewHolder,i);
        }
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==adapter.getItemCount()){
            return FOOTER_CODE;
        }else{
            return ITEM_CODE;
        }
    }

    class MyFooterViewHolder extends RecyclerView.ViewHolder{
     public TextView textView;
        public MyFooterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_footer);

        }
    }
}
