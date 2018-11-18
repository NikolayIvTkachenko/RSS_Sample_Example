package com.rsh.nikolay.samplerssexample.utils.services;

/** Created by Tkachenko Nikolay */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class ApiConnectionServiceApp {

    static final int MSG_OUT_GET_RSS_HABR = 2001;

    private Context connectionContext;
    private boolean serviceBound;

    private Messenger messageToService;
    private Messenger messageFromService;

    private ServiceConnection apiConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                IBinder service) {
            messageToService = new Messenger(service);
            serviceBound = true;
            ApiConnectionServiceApp.this.onServiceConnected();
            Log.d("APPRSSEXAMPLE", "ApiConnectionServiceApp onServiceConnected");
        }
        public void onServiceDisconnected(ComponentName className) {
            serviceBound = false;
            Log.d("APPRSSEXAMPLE", "ApiConnectionServiceApp onServiceDisconnected");
        }
    };

    public ApiConnectionServiceApp(Context context) {
        connectionContext = context;

    }

    public boolean isBound() {
        return serviceBound;
    }


    public void bindService() {
        Log.d("APPRSSEXAMPLE", "ApiConnectionServiceApp bindService()");
        Intent intent = new Intent(connectionContext, ServiceApp.class);
        connectionContext.bindService(intent, apiConnection,
                Context.BIND_AUTO_CREATE);
    }


    public void unbindService() {
        Log.d("APPRSSEXAMPLE", "ApiConnectionServiceApp unbindService()");
        if (serviceBound) {
            connectionContext.unbindService(apiConnection);
            serviceBound = false;
        }
    }


    public Messenger getMessenger() {
        Log.d("APPRSSEXAMPLE", "ApiConnectionServiceApp getMessenger()");
        if (messageFromService == null)  {
            messageFromService  = new Messenger(new Handler(new Handler.Callback(){
                @Override
                public boolean handleMessage(Message message) {

                    switch (message.what){
                        case MSG_OUT_GET_RSS_HABR:
                            Log.d("APPRSSEXAMPLE", "ApiConnectionServiceApp MSG_OUT_GET_RSS_HABR");
                            postLoadRssHabrHabr();
                            return true;
                    }
                    return false;
                }
            }));
        }
        return messageFromService;
    }

    /** Блок метоов, который выполняется при создании сервиса и его отключении */
    public void onServiceConnected(){
        Log.d("APPRSSEXAMPLE", "ApiConnectionServiceApp onServiceConnected()");
    }


    /** Блок, предназначенных для обработки  в сервисе */
    /** Метод вызова данных для получения RSS frm HabrHabr */
    public void loadRssHabrHabr(){
        Log.d("APPRSSEXAMPLE", "ApiConnectionServiceApp loadRssHabrHabr()");
        Message message = Message.obtain(null, ServiceApp.MSG_GET_RSS_HABR);
        message.replyTo = getMessenger();
        try {
            messageToService.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    /** Блок методлв, который вызываются после выполнения в сервисе */
    /** Метод, который выполняется после подключения к службе */
    public void postServiceConnected(){
        Log.d("APPRSSEXAMPLE", "ApiConnectionServiceApp postServiceConnected()");
    }


    /** Метод, который выполняется после обращения к получения данным от сети, значения RSS из сети */
    public void postLoadRssHabrHabr(){
        Log.d("APPRSSEXAMPLE", "ApiConnectionServiceApp postLoadRssHabrHabr()");

    }


}
