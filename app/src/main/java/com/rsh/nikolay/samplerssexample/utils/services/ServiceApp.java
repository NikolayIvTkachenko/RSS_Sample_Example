package com.rsh.nikolay.samplerssexample.utils.services;

/** Created by Tkachenko Nikolay */

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.rsh.nikolay.samplerssexample.MainApplication;
import com.rsh.nikolay.samplerssexample.models.ChannelRss;
import com.rsh.nikolay.samplerssexample.models.ItemRss;
import com.rsh.nikolay.samplerssexample.utils.ConstantsApp;
import com.rsh.nikolay.samplerssexample.utils.database.RssDbManager;
import com.rsh.nikolay.samplerssexample.utils.modules.NetworkModule;
import com.rsh.nikolay.samplerssexample.utils.modules.XmlParserRss;

import java.util.ArrayList;

public class ServiceApp extends Service {

    /**Идентификаторы сообщений*/
    public static final int MSG_GET_RSS_HABR = 1001;
    /**Загрузка данных из сети, парсинг и запись в БД*/
    private static AsyncTask<Void, Void, Message> asyncLoadRssHabr;

    private Messenger mMessenger;


    @Override
    public void onCreate() {
        super.onCreate();
        mMessenger = new Messenger(handlerMessngerService);
        Log.d("APPRSSEXAMPLE", "ServiceApp onCreate() ");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("APPRSSEXAMPLE", "ServiceApp onDestroy() ");

    }

    private Handler handlerMessngerService = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {

            if (message.replyTo == null) return false;
            switch (message.what){
                case MSG_GET_RSS_HABR:
                    asyncGetRssHabrHabe(message.replyTo);
                    return true;
            }

            return false;
        }
    });




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("APPRSSEXAMPLE", "ServiceApp onBind() ");
        return mMessenger.getBinder();
    }


    private static synchronized  void asyncGetRssHabrHabe(final Messenger replyTo){

        asyncLoadRssHabr = new AsyncTask<Void, Void, Message>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.d("APPRSSEXAMPLE", "ServiceApp asyncGetRssHabrHabe  onPreExecute ");

            }
            @Override
            protected Message doInBackground(Void... voids) {
                Log.d("APPRSSEXAMPLE", "ServiceApp asyncGetRssHabrHabe  doInBackground ");
                Message resultMessage;
                //Выполняем запрос по сети на сервер
                String resultNetwork = NetworkModule.loadFromNetworkRssFrommHabrHabr(ConstantsApp.url_rss_habr);
                //Log.d("APPRSSEXAMPLE", "ServiceApp asyncGetRssHabrHabe  resultNetwork: "+resultNetwork);
                XmlParserRss xmlParserRss = new XmlParserRss(resultNetwork);
                //запускаем процесс парсинга
                xmlParserRss.process();

                //получаем объекты после парсинга из XML
                ChannelRss channelRss = xmlParserRss.getChannelRss();
                ArrayList<ItemRss> listItemRss = xmlParserRss.getListItemRss();

                /*if(listItemRss!=null) {
                    Log.d("APPRSSEXAMPLE", "ServiceApp asyncGetRssHabrHabe  listItemRss!=null");
                    Log.d("APPRSSEXAMPLE", "ServiceApp asyncGetRssHabrHabe  listItemRss.size: "+listItemRss.size());
                }else{
                    Log.d("APPRSSEXAMPLE", "ServiceApp asyncGetRssHabrHabe  listItemRss==null");
                }*/

                //записываем в БД данные, предварительно удаляя все данные, посредством удаления таблицы
                //и ее пересоздания.
                RssDbManager.getInstance(MainApplication.getInstance().getApplicationContext()).removeAllDataRssChannel();
                RssDbManager.getInstance(MainApplication.getInstance().getApplicationContext()).removeAllDattaRssItemChannel();

                RssDbManager.getInstance(MainApplication.getInstance().getApplicationContext()).addChannelRss(channelRss);
                for(ItemRss itemRss : listItemRss) {
                    RssDbManager.getInstance(MainApplication.getInstance()
                            .getApplicationContext()).addItemRssChannel(itemRss);
                }
                resultMessage = Message.obtain(null, ApiConnectionServiceApp.MSG_OUT_GET_RSS_HABR);
                return resultMessage;
            }
            @Override
            protected void onPostExecute(Message resultMessage) {
                //super.onPostExecute(resultMessage);
                Log.d("APPRSSEXAMPLE", "ServiceApp asyncGetRssHabrHabe onPostExecute ");
                sendMessageToApiConnection(replyTo, resultMessage);
            }
        };
        asyncLoadRssHabr.execute();
    }



    private static void sendMessageToApiConnection(Messenger replyTo, Message resultMessage){
        try {
            Message msg = Message.obtain(null, resultMessage.what, resultMessage.arg1, resultMessage.arg2);
            replyTo.send(msg);
            Log.d("APPRSSEXAMPLE", "ServiceApp sendMessageToApiConnection replyTo.send(resultMessage)");
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.d("APPRSSEXAMPLE", "ServiceApp RemoteException replyTo.send e="+e.toString());
        } catch (Exception e){
            e.printStackTrace();
            Log.d("APPRSSEXAMPLE", "ServiceApp Exception replyTo.send e="+e.toString());
        }
    }
}
