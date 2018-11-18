package com.rsh.nikolay.samplerssexample.utils.database;

/** Created by Tkachenko Nikolay */

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.rsh.nikolay.samplerssexample.models.ChannelRss;
import com.rsh.nikolay.samplerssexample.models.ItemRss;

import java.util.ArrayList;
import java.util.Arrays;

import static com.rsh.nikolay.samplerssexample.utils.database.AppContentProvider.CONTENT_URI_CHANNEL_RSS;
import static com.rsh.nikolay.samplerssexample.utils.database.AppContentProvider.CONTENT_URI_ITEM_RSS;

public class RssDbManager {
    private static RssDbManager instance;

    private ContentResolver resolver;

    public static RssDbManager getInstance(Context context){
        if(instance == null){
            instance = new RssDbManager();
            instance.resolver = context.getContentResolver();
        }
        return instance;
    }

    public RssDbManager(){
    }

    public ChannelRss getChannelRss(){
        //Получаем, только первую запись по каналу, по умолчанию
        //только для тестового режима, в реальной клиент-серверной система
        // доступ осуществляется либо по имени, либо по идентификатору.
        Cursor cursor = resolver.query(CONTENT_URI_CHANNEL_RSS, null,
                null, null, null);
        ChannelRss channelRss = new ChannelRss();
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            channelRss.setTitleChannel(cursor.getString(1));
            channelRss.setLinkChannel(cursor.getString(2));
            channelRss.setDescriptionChannel(cursor.getString(3));
            channelRss.setLanguageChannel(cursor.getString(4));
            channelRss.setManagingEditorChanel(cursor.getString(5));
            channelRss.setGeneratorChannel(cursor.getString(6));
            channelRss.setPubdateChannel(cursor.getString(7));
            channelRss.setLastbuilddateChannel(cursor.getString(8));
            channelRss.setImageChannel(cursor.getString(9));

        }
        return channelRss;
    }

    public ArrayList<ItemRss> getListRssItems(){
        Cursor cursor = resolver.query(CONTENT_URI_ITEM_RSS, null,
                null, null, null);
        ArrayList<ItemRss> list = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                ItemRss item = new ItemRss();
                item.setTitleItem(cursor.getString(1));
                item.setGuidItem (cursor.getString(2));
                item.setLinkItem(cursor.getString(3));
                item.setDescriptionItem(cursor.getString(4));
                item.setPubdateItem(cursor.getString(5));
                item.setDc_creatorItem(cursor.getString(6));
                String resultCategory = cursor.getString(7);
                ArrayList<String> listCategory
                        = new ArrayList<>(Arrays.asList(resultCategory.split(",")));
                item.setCategoryItemList(listCategory);
                list.add(item);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public void addChannelRss(ChannelRss channelRss){
        //Log.d("APPRSSEXAMPLE", "RssDbManager addChannelRss");
        //Log.d("APPRSSEXAMPLE", "RssDbManager channelRss:"+channelRss.toString());
        ContentValues values = new ContentValues(9);
        values.put(RssDbHelper.TABLE_CHANNEL_TITLE, channelRss.getTitleChannel());
        values.put(RssDbHelper.TABLE_CHANNEL_LINK, channelRss.getLinkChannel());
        values.put(RssDbHelper.TABLE_CHANNEL_DESCRIPTION, channelRss.getDescriptionChannel());
        values.put(RssDbHelper.TABLE_CHANNEL_LANGUAGE, channelRss.getLanguageChannel());
        values.put(RssDbHelper.TABLE_CHANNEL_MANAGING_EDITOR, channelRss.getManagingEditorChanel());
        values.put(RssDbHelper.TABLE_CHANNEL_GENERATOR, channelRss.getGeneratorChannel());
        values.put(RssDbHelper.TABLE_CHANNEL_PUBDATE, channelRss.getPubdateChannel());
        values.put(RssDbHelper.TABLE_CHANNEL_LASTBUILDDATE, channelRss.getLastbuilddateChannel());
        values.put(RssDbHelper.TABLE_CHANNEL_IMAGE, channelRss.getImageChannel());
        resolver.insert(CONTENT_URI_CHANNEL_RSS, values);
    }


    public void addItemRssChannel(ItemRss item){
        //Log.d("APPRSSEXAMPLE", "RssDbManager addItemRssChannel");
        //Log.d("APPRSSEXAMPLE", "RssDbManager item: "+item.toString());
        ContentValues values = new ContentValues(6);
        values.put(RssDbHelper.TABLE_ITEM_CHANNEL_TITLE, item.getTitleItem());
        values.put(RssDbHelper.TABLE_ITEM_CHANNEL_GUID, item.getGuidItem());
        values.put(RssDbHelper.TABLE_ITEM_CHANNEL_LINK, item.getLinkItem());
        values.put(RssDbHelper.TABLE_ITEM_CHANNEL_DESCRIPTION, item.getDescriptionItem());
        values.put(RssDbHelper.TABLE_ITEM_CHANNEL_PUBDATE, item.getPubdateItem());
        values.put(RssDbHelper.TABLE_ITEM_CHANNEL_DCCREATOR, item.getDc_creatorItem());

        String result = "";
        for(int i=0; i<item.getCategoryItemList().size();i++){
            if(i<(item.getCategoryItemList().size()-1)) {
                result += item.getCategoryItemList().get(i) + ",";
            }else {
                result += item.getCategoryItemList().get(i);
            }
        }
        values.put(RssDbHelper.TABLE_ITEM_CHANNEL_CATEGORY, result);

        resolver.insert(CONTENT_URI_ITEM_RSS, values);
    }

    //В тестовом режиме не реализуем механизм  update для ItemRss
    public void updateItemRssChannel(ItemRss item){

    }

    //В тестовом режиме не реализуем механизм  update для ChannelRss
    public void updateRssChannel(ChannelRss channelRss){

    }

    public void deleteItemRssChannel(int rssItemId){

    }

    public void deleteRssChannel(int rssChannelId){

    }

    public void removeAllDataRssChannel(){
        resolver.delete(CONTENT_URI_CHANNEL_RSS, null, null);
    }

    public void removeAllDattaRssItemChannel(){
        resolver.delete(CONTENT_URI_ITEM_RSS, null, null);
    }
}
