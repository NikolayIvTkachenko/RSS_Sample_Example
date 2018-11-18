package com.rsh.nikolay.samplerssexample.utils.modules;

/** Created by Tkachenko Nikolay */

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkModule {


    public static String loadFromNetworkRssFrommHabrHabr(String urlPath){
        String result = "";
        Log.d("APPRSSEXAMPLE", "NetworkModule loadFromNetworkRssFrommHabrHabr");
        Log.d("APPRSSEXAMPLE", "urlPath="+urlPath);

        StringBuilder tempBuffer = new StringBuilder();
        HttpURLConnection connection = null;
        try{

            URL url = new URL(urlPath);
            connection = (HttpURLConnection)url.openConnection();

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int charRead;
            char[] inputBuffer = new char[500];

            int responseCode = connection.getResponseCode();
            Log.d("APPRSSEXAMPLE",
                    "NetworkModule loadFromNetworkRssFrommHabrHabr responseCode="+responseCode);

            if (HttpURLConnection.HTTP_OK == responseCode) {
                while(true){
                    charRead = isr.read(inputBuffer);
                    if(charRead<=0){
                        break;
                    }
                    tempBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));
                }
                result = tempBuffer.toString();
                return result;
            }


        }catch (IOException e){
            Log.d("APPRSSEXAMPLE", " IO exception reading data = "+ e.toString());
        }finally {
            if(connection!=null)connection.disconnect();
        }
        return result;
    }
}
