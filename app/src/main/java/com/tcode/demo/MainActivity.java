package com.tcode.demo;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.tcode.demo.adapter.MainAdapter;
import com.tcode.demo.bean.ListData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainActivity mContext;

    private RecyclerView mainRecyclerView;
    private SwipeRefreshLayout mainSwipeRefreshLayout;
    private LinearLayoutManager mLinearLayoutManager;//刷新和加载更多的管理
    private LinearLayout pgb_bottom;
    private List<ListData> list;
    private Handler handler = new Handler();
    private MainAdapter mainAdapter;

    int k =1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();//关联控件
        initData();//初始化数据
        initView();//初始化视图

    }

    private void init()
    {
        mContext = this;
        mainRecyclerView = (RecyclerView)findViewById(R.id.mainRecyclerView);
        mainSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.mainSwipeRefreshLayout);
        pgb_bottom = (LinearLayout)findViewById(R.id.pgb_bottom);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
    }

    private void initData()
    {
        list = new ArrayList<>();
        for (int i = 0;i < 5 ;i ++)
        {
            ListData listData = new ListData("group-1  item-",i);
            list.add(listData);
        }
    }

    private void initView()
    {
        mainAdapter = new MainAdapter(mContext,list);
        mainRecyclerView.setLayoutManager(mLinearLayoutManager);
        mainRecyclerView.setAdapter(mainAdapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));


        //下拉刷新
        mainSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mainSwipeRefreshLayout.setRefreshing(true);
                moreData();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainAdapter.notifyDataSetChanged();
                        mainSwipeRefreshLayout.setRefreshing(false);//停止刷新动画
                    }
                }, 1000);

            }
        });


        //上拉加载
        mainRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //判断是否滑动到底部
                if (!recyclerView.canScrollVertically(1)) {
                    pgb_bottom.setVisibility(View.VISIBLE);
                    moreData();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pgb_bottom.setVisibility(View.INVISIBLE);
                            mainAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
            }
        });
    }


    //更所数据
    private void moreData()
    {
        k++;
        for (int i = 0;i < 9 ;i ++)
        {
            ListData listData = new ListData("group-"+k+"  item",i);
            list.add(listData);
        }
    }



}
