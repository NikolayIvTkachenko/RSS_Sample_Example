package com.rsh.nikolay.samplerssexample.View;

/** Created by Tkachenko Nikolay */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.rsh.nikolay.samplerssexample.MainApplication;
import com.rsh.nikolay.samplerssexample.R;
import com.rsh.nikolay.samplerssexample.models.ChannelRss;
import com.rsh.nikolay.samplerssexample.models.ItemRss;
import com.rsh.nikolay.samplerssexample.presenter.InterfaceMVP;
import com.rsh.nikolay.samplerssexample.presenter.MainPresenter;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity
        implements InterfaceMVP.ProgressView, InterfaceMVP.ViewHandle, InterfaceMVP.ToolBarSetting{


    private MainPresenter mainPresenter;

    public RecyclerViewRssAdapter recyclerViewRssAdapter;

    //progressBarActivityRss
    private ProgressBar pbActivityRss;

    //recyclerViewListRss
    private RecyclerView rvListRss;

    //pull_to_refresh
    private SwipeRefreshLayout pullToRefresh;

    //fab
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("APPRSSEXAMPLE", "MainActivity onCreate()");

        pbActivityRss = (ProgressBar)findViewById(R.id.progress_bar_activity_rss);
        rvListRss = (RecyclerView)findViewById(R.id.recycler_view_list_rss);
        rvListRss.setLayoutManager(new LinearLayoutManager(this));

        pullToRefresh = (SwipeRefreshLayout)findViewById(R.id.pull_to_refresh);

        //fab
        floatingActionButton =(FloatingActionButton)findViewById(R.id.fab);

        if(mainPresenter==null){
            Log.d("APPRSSEXAMPLE", "mainPresenter==null -> create MainPresenter");
            mainPresenter = new MainPresenter();
        }

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.pressOnButtonGetRss();
                floatingActionButton.setEnabled(false);
            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("APPRSSEXAMPLE", "MainActivity onStart()");
        mainPresenter.onProgressViewAttach(this);
        mainPresenter.onViewAttched(this);
        mainPresenter.boundServiceNetwork();
        mainPresenter.setToolBarSetting(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("APPRSSEXAMPLE", "MainActivity onResume()");

        if(MainApplication.getInstance().KEY_CHANGE_ORIENTATION_SCREEN){
            mainPresenter.updateDataFromDB();
            mainPresenter.updateDateFromDbChannel();
            MainApplication.getInstance().KEY_CHANGE_ORIENTATION_SCREEN = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("APPRSSEXAMPLE", "MainActivity onPause()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("APPRSSEXAMPLE", "MainActivity onStop()");
        mainPresenter.unboyndServiceNetwork();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("APPRSSEXAMPLE", "MainActivity onDestroy()");
        mainPresenter.onViewDetach();
        mainPresenter.onProgressViewDetach();
    }


    @Override
    public void showProgressiveView() {
        Log.d("APPRSSEXAMPLE", "MainActivity showProgressiveView");
        if(MainApplication.getInstance().KEY_PRESS_BUTTON_UPDATE) {
            pbActivityRss.setVisibility(VISIBLE);
        }
    }

    @Override
    public void hideProgressiveView() {
        Log.d("APPRSSEXAMPLE", "MainActivity hideProgressiveView");
        if(MainApplication.getInstance().KEY_PRESS_BUTTON_UPDATE) {
            pbActivityRss.setVisibility(GONE);
        }
    }

    @Override
    public void setListRss(ArrayList<ItemRss> listRss) {
        Log.d("APPRSSEXAMPLE", "MainActivity setListRss");
        updateRecyclerView(listRss);
    }


    private void updateRecyclerView(ArrayList<ItemRss> listRss){
        pullToRefresh.setRefreshing(false);
        recyclerViewRssAdapter = new RecyclerViewRssAdapter(listRss);
        rvListRss.setAdapter(recyclerViewRssAdapter);
        if(MainApplication.getInstance().KEY_PRESS_BUTTON_UPDATE ){
            MainApplication.getInstance().KEY_PRESS_BUTTON_UPDATE = false;
        }
        floatingActionButton.setEnabled(true);
    }

    public void pressBtnLoadData(View view){

        MainApplication.getInstance().KEY_PRESS_BUTTON_UPDATE = true;
        mainPresenter.pressOnButtonGetRss();
        floatingActionButton.setEnabled(false);
    }


    @Override
    public void setTitleToolbar(ChannelRss channelRss) {
        getSupportActionBar().setTitle(channelRss.getTitleChannel() + "  "+ channelRss.getPubdateChannel());
    }
}
