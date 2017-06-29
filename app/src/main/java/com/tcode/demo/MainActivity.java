package com.tcode.demo;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tcode.demo.adapter.MainAdapter;
import com.tcode.demo.bean.ListData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainActivity mContext;

    private RecyclerView mainRecyclerView;
    private SwipeRefreshLayout mainSwipeRefreshLayout;
    private LinearLayoutManager mLinearLayoutManager;//刷新和加载更多的管理
    private List<ListData> list;//数据列表
    private MainAdapter mainAdapter;
    private ListData more;//footer列

    private boolean isScroll = true;
    private boolean isScrolling = false;
    private int j = 1;
    float mPosX,mPosY,mCurPosX,mCurPosY;

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
        mLinearLayoutManager = new LinearLayoutManager(mContext);
    }

    private void initData()
    {
        list = new ArrayList<>();
        for (int i = 0;i < 4 ;i ++)
        {
            ListData listData = new ListData("group-1  item-",i,0);
            list.add(listData);
        }
        more = new ListData("",0,1);
    }

    private void initView()
    {
        mainAdapter = new MainAdapter(mContext,list);
        mainRecyclerView.setLayoutManager(mLinearLayoutManager);
        mainRecyclerView.setAdapter(mainAdapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));

        //下拉刷新
        mainSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        //清空list数据
                        list.clear();
                        //模拟刷新后的数据
                        for (int i = 0;i < 5 ;i ++)
                        {
                            ListData listData = new ListData("刷新后的 item-",i,0);
                            list.add(listData);
                        }
                        mainAdapter.notifyDataSetChanged();//通知adapter刷新数据
                        mainSwipeRefreshLayout.setRefreshing(false);//关闭刷新进度条
                    }
                }, 2000);
            }
        });

        //上拉加载
        mainRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && isScroll && isScrolling)
                {
                    int lastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = mLinearLayoutManager.getItemCount();

                    if (lastVisibleItem == (totalItemCount - 1))
                    {
                        LoadMore();//加载更多数据
                        isScroll = false;
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0)
                {
                    isScrolling = true;
                }
                else
                {
                    isScrolling = false;
                }
            }
        });

    }

    //添加手势的判断
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mPosX = event.getX();
                mPosY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurPosX = event.getX();
                mCurPosY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (mCurPosY - mPosY > 0 && (Math.abs(mCurPosY - mPosY) > 25))
                {
                    //向下滑動
                    isScrolling = false;
                }
                else if (mCurPosY - mPosY < 0 && (Math.abs(mCurPosY - mPosY) > 25))
                {
                    //向上滑动
                    isScrolling = true;
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    public void onPreLoadMore()
    {
        list.add(more);
        mainAdapter.notifyDataSetChanged();
    }

    public void onPostLoadMore()
    {
        list.remove(more);
        isScroll=true;
        mainAdapter.notifyDataSetChanged();
    }

    //加载更多的模拟数据
    private void LoadMore()
    {
        j++;

        onPreLoadMore();
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                for (int i = 0;i < 10 ;i ++)
                {
                    ListData listData = new ListData("更多 group "+ j +"  item-",i,0);
                    list.add(listData);
                }
                onPostLoadMore();
            }
        }, 2000);
    }

}
