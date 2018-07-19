package com.example.load;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Observable;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow
        );
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        setData();
        RefreshAdapter refreshAdapter = new RefreshAdapter(mList, this);
        recyclerView.setAdapter(refreshAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
    }


    private void setData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("第" + i + "个");
        }
    }

    @Override
    public void onRefresh() {
        Observable
                .timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map(new Func1<Long, Object>() {
                    @Override
                    public Object call(Long aLong) {
                        fetchingNewData();
                        swipeRefreshLayout.setRefreshing(false);
                        refreshAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Refresh Finished!", Toast.LENGTH_SHORT).show();
                        return null;
                    }
                }).subscribe();
    }

    private void fetchingNewData() {
        mList.add(0, "下拉刷新出来的数据");
    }
    recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
        @Override
        public void onLoadMore(int currentPage) {
            simulateLoadMoreData();
        }
    });

    private void simulateLoadMoreData() {
        Observable
                .timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map(new Func1<Long, Object>() {
                    @Override
                    public Object call(Long aLong) {
                        loadMoreData();
                        stringAdapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Load Finished!", Toast.LENGTH_SHORT).show();
                        return null;
                    }
                }).subscribe();
    }

    private void loadMoreData() {
        List<String> moreList = new ArrayList<>();
        for (int i = 10; i < 13; i++) {
            moreList.add("加载更多的数据");
        }
        mList.addAll(moreList);
    }