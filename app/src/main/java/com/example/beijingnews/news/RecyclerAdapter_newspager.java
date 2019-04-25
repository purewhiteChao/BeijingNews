package com.example.beijingnews.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.beijingnews.R;
import com.example.beijingnews.model.bean.NewPagerBean;
import com.example.beijingnews.model.bean.NewsRecyviewBean;
import com.example.beijingnews.model.uri.UploadURI;

import java.util.List;

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2019/4/22 0022
 * Time: 17:38
 * Describe: ${as}
 */
public class RecyclerAdapter_newspager extends RecyclerView.Adapter<RecyclerAdapter_newspager.MyViewHolder> {
    private List<NewPagerBean.DataBean.NewsBean> list;
    private Context context;

    public void refresh(List<NewPagerBean.DataBean.NewsBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    public RecyclerAdapter_newspager(List<NewPagerBean.DataBean.NewsBean> list){
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_newpager,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.title.setText(list.get(i).getTitle());
        myViewHolder.date.setText(list.get(i).getPubdate());
        Glide.with(context).load(UploadURI.dowmPicture+list.get(i).getListimage().substring(15)).into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview_recycler_newspager);
            title = itemView.findViewById(R.id.title_recycler_newspager);
            date = itemView.findViewById(R.id.date_recycler_newspager);
        }
    }
}
