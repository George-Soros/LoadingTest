package com.tj.loadingtest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{

    @Bind(R.id.loading_one)
    Button mLoadingONE;
    @Bind(R.id.loading_two)
    Button mLoadingTWO;
    @Bind(R.id.loading_three)
    Button mLoadingTHREE;

    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActivity = MainActivity.this;
        ButterKnife.bind(this);

        initEvent();
    }

    public void initEvent(){
        mLoadingONE.setOnClickListener(this);
        mLoadingTWO.setOnClickListener(this);
        mLoadingTHREE.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loading_one:
                View view1 = LayoutInflater.from(mActivity)
                         .inflate(R.layout.loading_dialog, null);
                 createLoadingDialog(mActivity, view1);
                break;
            case R.id.loading_two:
                View view2 = LayoutInflater.from(mActivity)
                        .inflate(R.layout.loading_dialog_two, null);
                createLoadingDialog(mActivity, view2);
                break;
            case R.id.loading_three:
                startActivity(new Intent(mActivity, RefreshActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 简单的dialog
     * @return
     */
    public static Dialog createLoadingDialog(Context context, View view){
        Dialog mDialog = new Dialog(context, R.style.popupDialog);
        mDialog.setContentView(view);
//        mDialog.setCancelable(false);
//        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        return mDialog;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
