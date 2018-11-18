package com.rsh.nikolay.samplerssexample.presenter;

/** Created by Tkachenko Nikolay */

import android.util.Log;

import com.rsh.nikolay.samplerssexample.MainApplication;
import com.rsh.nikolay.samplerssexample.models.ChannelRss;
import com.rsh.nikolay.samplerssexample.models.ItemRss;
import com.rsh.nikolay.samplerssexample.utils.database.RssDbManager;
import com.rsh.nikolay.samplerssexample.utils.services.ApiConnectionServiceApp;

import java.util.ArrayList;

public class MainPresenter implements InterfaceMVP.ApiServiceNetworkBounds, InterfaceMVP.Presenter {


    private InterfaceMVP.ProgressView progressView;
    private InterfaceMVP.ViewHandle viewHandle;
    private InterfaceMVP.ToolBarSetting toolBarSetting;

    private ArrayList<ItemRss> listItemsRss = null;

    private ApiConnectionServiceApp apiConnectionServiceApp;

    public MainPresenter(){
        apiConnectionServiceApp = getApiConnectionServiceApp();

    }

    public void setToolBarSetting(InterfaceMVP.ToolBarSetting toolBarSetting){
        this.toolBarSetting = toolBarSetting;
    }

    @Override
    public void boundServiceNetwork() {
        Log.d("APPRSSEXAMPLE", "MainPresenter boundServiceNetwork()");
        if(!apiConnectionServiceApp.isBound()) {
            apiConnectionServiceApp.bindService();
        }
        Log.d("APPRSSEXAMPLE", "MainPresenter apiConnectionServiceApp.isBound():"+apiConnectionServiceApp.isBound());
    }

    @Override
    public void unboyndServiceNetwork() {
        Log.d("APPRSSEXAMPLE", "MainPresenter boundServiceNetwork()");
        if(apiConnectionServiceApp.isBound()) {
            apiConnectionServiceApp.unbindService();
        }
    }



    @Override
    public void onViewAttched(InterfaceMVP.ViewHandle viewHandle) {
        Log.d("APPRSSEXAMPLE", "MainPresenter onViewAttched");
        this.viewHandle = viewHandle;
    }

    @Override
    public void onViewDetach() {
        Log.d("APPRSSEXAMPLE", "MainPresenter onViewDetach()");
        this.viewHandle = null;
    }

    @Override
    public void pressOnButtonGetRss() {
        Log.d("APPRSSEXAMPLE", "MainPresenter pressOnButtonGetRss()");
        progressView.showProgressiveView();
        apiConnectionServiceApp.loadRssHabrHabr();
    }

    @Override
    public void onProgressViewAttach(InterfaceMVP.ProgressView progressView) {
        Log.d("APPRSSEXAMPLE", "MainPresenter onProgressViewAttach");
        this.progressView = progressView;
    }

    @Override
    public void onProgressViewDetach() {
        Log.d("APPRSSEXAMPLE", "MainPresenter onProgressViewDetach");
        this.progressView = null;
    }

    @Override
    public void updateDataFromDB() {
        listItemsRss =
                RssDbManager.getInstance(MainApplication.getInstance().getApplicationContext()).getListRssItems();

        viewHandle.setListRss(listItemsRss);
    }

    @Override
    public void updateDateFromDbChannel() {
        ChannelRss channelRss = RssDbManager.getInstance(MainApplication.getInstance().getApplicationContext()).getChannelRss();
        toolBarSetting.setTitleToolbar(channelRss);
    }


    public ApiConnectionServiceApp getApiConnectionServiceApp() {
        apiConnectionServiceApp
                = new ApiConnectionServiceApp(MainApplication.getInstance().getApplicationContext()){

            @Override
            public void onServiceConnected() {
                super.onServiceConnected();
                Log.d("APPRSSEXAMPLE", "MainPresenter onServiceConnected() ");


            }

            @Override
            public void postServiceConnected() {
                super.postServiceConnected();
                Log.d("APPRSSEXAMPLE", "MainPresenter postServiceConnected() ");
                //Делаем активной кнопку запрос на сервер


            }

            @Override
            public void postLoadRssHabrHabr() {
                super.postLoadRssHabrHabr();
                Log.d("APPRSSEXAMPLE", "MainPresenter postLoadRssHabrHabr() " +
                        "- выполняем команды после загрузки данных с сервевера ");
                progressView.hideProgressiveView();

                updateDataFromDB();
                updateDateFromDbChannel();
            }
        };
        return apiConnectionServiceApp;
    }




}
