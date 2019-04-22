package com.example.beijingnews.main;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.beijingnews.R;
import com.example.beijingnews.base.MVPActivity;
import com.example.beijingnews.model.bean.AvatarBean;
import com.example.beijingnews.model.bean.NewsBean;
import com.example.beijingnews.model.uri.UploadURI;
import com.example.beijingnews.view.customview.AvatarView;
import com.example.beijingnews.view.fragment.GovaffairsFragment;
import com.example.beijingnews.view.fragment.HomeFragment;
import com.example.beijingnews.view.fragment.NewsFragment;
import com.example.beijingnews.view.fragment.ServiceFragment;
import com.example.beijingnews.view.fragment.SettingFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends MVPActivity<MainPresenter, MainView, NewsBean> implements MainView, View.OnClickListener {

    @BindView(R.id.text_toolbar_main)
    TextView textToolbarMain;
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.framelayout_main)
    FrameLayout framelayoutMain;
    @BindView(R.id.bottomNavigation_main)
    BottomNavigationView bottomNavigationMain;
    @BindView(R.id.drawer_main)
    DrawerLayout drawerMain;
    private long lastTime = 0;
    private NavigationView navigation_main;
    private View headerView;
    private AvatarView image_header;
    private TextView text_header;
    private final int CREAME_CODE = 100;
    private Fragment fragment = null;
    private List<Fragment> list_Fragment;
    public List<NewsBean.DataBean> data1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        setUpView();
    }

    /**
     * TODO----------------------------------setUpView----------------------------------------------
     */
    private void setUpView() {
        navigation_main.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String title = menuItem.getTitle().toString();
                switch (title) {
                    case "新闻":
                        break;
                    case "专题":

                        break;
                    case "组图":

                        break;
                    case "互动":
                        break;
                }
                return true;
            }
        });
        bottomNavigationMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String title = menuItem.getTitle().toString();
                switch (title) {
                    case "首页":
                        toolbarMain.setNavigationIcon(null);
                        setFragment(list_Fragment.get(0));
                        break;
                    case "新闻":
                        toolbarMain.setNavigationIcon(R.drawable.img_menu);
                        setFragment(list_Fragment.get(1));
                        break;
                    case "服务":
                        toolbarMain.setNavigationIcon(R.drawable.img_menu);
                        setFragment(list_Fragment.get(2));

                        break;
                    case "政务":
                        toolbarMain.setNavigationIcon(R.drawable.img_menu);
                        setFragment(list_Fragment.get(3));

                        break;
                    case "设置":
                        toolbarMain.setNavigationIcon(null);
                        setFragment(list_Fragment.get(4));

                        break;
                }
                textToolbarMain.setText(title);
                return true;
            }
        });
        bottomNavigationMain.setSelectedItemId(R.id.news_menu_buttomnavigation);
    }

    /**
     * TODO-----------------------------初始化控件数据----------------------------------------------------
     */
    private void initView() {
        navigation_main = (NavigationView) findViewById(R.id.navigation_main);
        headerView = navigation_main.getHeaderView(0);
        image_header = headerView.findViewById(R.id.image_header);
        text_header = headerView.findViewById(R.id.text_header);
        image_header.setOnClickListener(this);
        text_header.setOnClickListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerMain,toolbarMain,R.string.app_name,R.string.app_name);
        toggle.syncState();
        drawerMain.addDrawerListener(toggle);
    }

    private void initData() {
        list_Fragment = new ArrayList<>();
        list_Fragment.add(new HomeFragment());
        list_Fragment.add(new NewsFragment());
        list_Fragment.add(new ServiceFragment());
        list_Fragment.add(new GovaffairsFragment());
        list_Fragment.add(new SettingFragment());
        presenter.getNewsData(UploadURI.localZHBJ);
    }

    /**
     * TODO------------------------普通方法区---------------------------------------------------------
     */
    private void setFragment(Fragment fragment){
        this.getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_main,fragment).commit();
    }

    private void openCreame() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, CREAME_CODE);
    }

    @Override
    public void onBackPressed() {
        Long curTime = System.currentTimeMillis();
        if(curTime-lastTime>2000){
            Toast.makeText(context, "再次点击返回退出", Toast.LENGTH_SHORT).show();
            lastTime = curTime;
        }else{
            super.onBackPressed();
        }
    }

    /**
     * ToDo-------------------------ActivityResult---------------------------------------------------
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREAME_CODE && data != null) {
            Uri uri = data.getData();
            Cursor query = getContentResolver().query(uri, null, null, null, null);
            query.moveToFirst();
            String path = query.getString(query.getColumnIndex("_data"));

            presenter.getAvaterData(UploadURI.uploadURI, path);
            query.close();

        }
    }

    /**
     * ToDo ------------------------------------点击方法区-----------------------------------------------------------
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_header:
                openCreame();
                break;
            case R.id.text_header:
                break;
        }
    }

    /**
     * Todo------------------------------BaseView回调方法区--------------------------------------------------------
     */
    @Override
    public void success(NewsBean data) {

        Log.i("GC", data.getData().get(0).getTitle());
        data1 = data.getData();
        EventBus.getDefault().post(data1.get(0).getChildren());
        Menu menu = navigation_main.getMenu();
        for (int i = 0; i < data1.size(); i++) {
            NewsBean.DataBean dataBean = data1.get(i);
            String title = dataBean.getTitle();
            MenuItem add = menu.add(title);
            add.setIcon(R.drawable.menu_arr_select);
        }
    }

    @Override
    public void failless(String ss) {

        Log.i("GC", ss);
    }

    @Override
    public void getAvater(AvatarBean bean) {

        AvatarBean.DataBean data = bean.getData();
        String path = data.getUrl();
        Log.i("guochao", path);
        Glide.with(context).load(path).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                if (e != null) {
                    Log.i("GC", "onLoadFailed:" + e.getMessage());
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

    /**
     * ToDo-------------------------ContextView区----------------------------------------
     */
    @Override
    protected int getContextView() {
        return R.layout.drawlayout_main;
    }

    @Override
    public MainPresenter initPresenter() {
        return new MainPresenter();
    }
}
