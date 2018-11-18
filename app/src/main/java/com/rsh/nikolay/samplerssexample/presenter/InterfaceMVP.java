package com.rsh.nikolay.samplerssexample.presenter;

/** Created by Tkachenko Nikolay */

import com.rsh.nikolay.samplerssexample.models.ChannelRss;
import com.rsh.nikolay.samplerssexample.models.ItemRss;

import java.util.ArrayList;

public interface InterfaceMVP {

    interface ProgressView{
        void showProgressiveView();
        void hideProgressiveView();
    }

    interface Presenter{
        void onViewAttched (ViewHandle viewHandle);
        void onViewDetach();
        void pressOnButtonGetRss();
        void onProgressViewAttach(ProgressView progressView);
        void onProgressViewDetach();
        void updateDataFromDB();
        void updateDateFromDbChannel();
    }

    interface ViewHandle{
        void setListRss(ArrayList<ItemRss> listRss);
    }

    interface ApiServiceNetworkBounds{
        void boundServiceNetwork();
        void unboyndServiceNetwork();
    }

    interface ToolBarSetting{
        void setTitleToolbar(ChannelRss channelRss);
    }
}
