package com.rsh.nikolay.samplerssexample;


/** Created by Tkachenko Nikolay */

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

public class MainApplication extends Application {

    private static MainApplication instance;

    //ключ, который говорит о смене экрана
    public boolean KEY_CHANGE_ORIENTATION_SCREEN = false;

    public boolean KEY_PRESS_BUTTON_UPDATE= false;

    @Override
    public void onCreate () {
        super.onCreate();
        Log.d("APPRSSEXAMPLE", "MainApplication onCreate()");
        instance = this;

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("APPRSSEXAMPLE", "MainApplication onTerminate()");
    }


    public static MainApplication getInstance(){
        Log.d("APPRSSEXAMPLE", "MainApplication getInstance()");
        return instance;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("APPRSSEXAMPLE", "MainApplication onConfigurationChanged");
        KEY_CHANGE_ORIENTATION_SCREEN = true;
    }
}
