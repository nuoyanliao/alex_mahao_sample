package com.mahao.alex.systemwidgetdemo.recycleView.swipetoloadlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.mahao.alex.systemwidgetdemo.R;
import com.mahao.alex.systemwidgetdemo.recycleView.DividerItemDecoration;
import com.mahao.alex.systemwidgetdemo.recycleView.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * 三方库SwipeToLayout的使用  实现RecycleView的上拉刷新和下拉加载
 *
 *  需要导入一下依赖
 *
 *   repositories {
 *       maven { url "https://jitpack.io" }
 *          }
 *
 *      compile 'com.github.Aspsine:SwipeToLoadLayout:1.0.3'
 *
 * Created by Alex_MaHao on 2016/5/10.
 */
public class SwipeToLayoutActivity extends AppCompatActivity implements OnRefreshListener, OnLoadMoreListener {



    private RecyclerView mRecycleView;

    SwipeToLoadLayout swipeToLoadLayout;
    private List<String> datas = new ArrayList<>();

    private HomeAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_swipetolayout);


        swipeToLoadLayout = ((SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout));


        mRecycleView = ((RecyclerView) findViewById(R.id.swipe_target));


        adapter = new HomeAdapter();

        //设置垂直的线性布局管理器，Orientation -->   VERTICAL:垂直   HORIZONTAL:水平
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //添加分割线
        mRecycleView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL_LIST));

        mRecycleView.setLayoutManager(layoutManager);

        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mRecycleView.setAdapter(adapter);


        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);

    }




    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.add();
                swipeToLoadLayout.setRefreshing(false);
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.refresh();
                swipeToLoadLayout.setLoadingMore(false);
            }
        },2000);
    }
}