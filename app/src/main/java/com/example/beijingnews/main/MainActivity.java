package com.example.beijingnews.main;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.beijingnews.R;
import com.example.beijingnews.base.MVPActivity;
import com.example.beijingnews.model.bean.AvatarBean;
import com.example.beijingnews.model.bean.Bean;
import com.example.beijingnews.model.uri.UploadURI;
import com.example.beijingnews.view.customview.AvatarView;

import org.w3c.dom.Text;

public class MainActivity extends MVPActivity<MainPresenter, MainView, Bean> implements MainView, View.OnClickListener {

    private NavigationView navigation_main;
    private View headerView;
    private AvatarView image_header;
    private TextView text_header;
    private  final int CREAME_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    @Override
    protected int getContextView() {
        return R.layout.drawlayout_main;
    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void success(Bean data) {

        Log.i("GC", data.getData().get(0).getTitle());
    }

    @Override
    public void failless(String ss) {

        Log.i("GC", ss);
    }

    private void initView() {
        navigation_main = (NavigationView) findViewById(R.id.navigation_main);
        headerView = navigation_main.getHeaderView(0);
        image_header = headerView.findViewById(R.id.image_header);
        text_header = headerView.findViewById(R.id.text_header);
        image_header.setOnClickListener(this);
        text_header.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_header:
                openCreame();
                break;
            case R.id.text_header:
                break;
        }
    }

    private void openCreame() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, CREAME_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CREAME_CODE&&data!=null){
            Uri uri = data.getData();
            Cursor query = getContentResolver().query(uri, null, null, null, null);
            query.moveToFirst();
            String path = query.getString(query.getColumnIndex("_data"));

            presenter.getData(UploadURI.uploadURI,path);
            query.close();

        }
    }

    @Override
    public void getAvater(AvatarBean bean) {

        AvatarBean.DataBean data = bean.getData();
        String path = data.getUrl();
        Log.i("guochao",path);
        Glide.with(context).load(path).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                if(e!=null){
                    Log.i("GC","onLoadFailed:"+e.getMessage());
                }
                return true;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                image_header.setImageDrawable(resource);
                image_header.notifyViewChange();
                return true;
            }
        }).into(image_header);
    }
}
