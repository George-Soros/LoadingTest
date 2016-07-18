package com.tj.loadingtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tj.mymodule.MaterialRefreshLayout;
import com.tj.mymodule.MaterialRefreshListener;

/**
 * Created by Administrator on 2016/7/12 0012.
 *
 * 仿间书的下拉刷新和上拉加载
 */
public class RefreshActivity extends AppCompatActivity{

    private MaterialRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = RefreshActivity.this;
        setContentView(R.layout.activity_refresh);

        mRefreshLayout = (MaterialRefreshLayout)
                findViewById(R.id.material_refresh_layout);
        mRecyclerView = (RecyclerView)
                findViewById(R.id.recycler_view_layout);

        //设置RecyclerView的adapter
        setRecyclerViewAdapter();

        //设置下拉刷新
        setRefreshLayout();
    }

    public void setRecyclerViewAdapter(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mAdapter = new MyAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void setRefreshLayout(){
        mRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {

                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRefreshLayout.finishRefresh();
                    }
                }, 3000);

                materialRefreshLayout.finishRefreshLoadMore();
            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                Toast.makeText(RefreshActivity.this, "load more", Toast.LENGTH_LONG).show();

                materialRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        materialRefreshLayout.finishRefreshLoadMore();

                    }
                }, 3000);
            }

            @Override
            public void onfinish() {
                Toast.makeText(mContext, "finish", Toast.LENGTH_LONG).show();
            }
        });
    }






}
