package com.rsh.nikolay.samplerssexample.utils.modules;

/** Created by Tkachenko Nikolay */

import android.util.Log;

import com.rsh.nikolay.samplerssexample.models.ChannelRss;
import com.rsh.nikolay.samplerssexample.models.ItemRss;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class XmlParserRss {

    private String xmlData;
    private ArrayList<ItemRss> listItemRss;
    private ChannelRss channelRss = null;

    public XmlParserRss(String xmlData){
        Log.d("APPRSSEXAMPLE", "XmlParserRss create XmlParserRss constructor");
        this.xmlData = xmlData;
        listItemRss = new ArrayList<ItemRss>();
        channelRss = new ChannelRss();
    }

    public ChannelRss getChannelRss() {return channelRss; }
    public ArrayList<ItemRss> getListItemRss() {
        return listItemRss;
    }



    public boolean process(){
        Log.d("APPRSSEXAMPLE", "XmlParserRss process()");
        boolean status = true;
        ItemRss currentRecord = null;

        boolean inEntry = false;
        String textValue = "";

        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(this.xmlData));
            int eventType = xpp.getEventType();


            while(eventType != XmlPullParser.END_DOCUMENT){
                String tagName = xpp.getName();

                switch(eventType){

                    case XmlPullParser.START_TAG:
                        //Log.d("APPRSSEXAMPLE", "XmlParserRss Starting tag for "+tagName);
                        if(tagName.equalsIgnoreCase("item")){
                            inEntry = true;
                            currentRecord = new ItemRss();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        //Log.d("APPRSSEXAMPLE", "XmlParserRss Starting tag for "+tagName);
                        if(inEntry){
                            if(tagName.equalsIgnoreCase("item")){
                                listItemRss.add(currentRecord);
                                inEntry = false;
                            }else if(tagName.equalsIgnoreCase("title")){
                                currentRecord.setTitleItem(textValue);
                            }else if(tagName.equalsIgnoreCase("guid")){
                                currentRecord.setGuidItem(textValue);
                            }else if(tagName.equalsIgnoreCase("link")){
                                currentRecord.setLinkItem(textValue);
                            }else if(tagName.equalsIgnoreCase("description")){
                                currentRecord.setDescriptionItem(textValue);
                            }else if(tagName.equalsIgnoreCase("pubdate")){
                                currentRecord.setPubdateItem(textValue);
                            }else if(tagName.equalsIgnoreCase("dc:creator")){
                                currentRecord.setDc_creatorItem(textValue);
                            }else if(tagName.equalsIgnoreCase("category")){
                                currentRecord.setItemCategoryItemList(textValue);
                            }
                        }else{
                            if(tagName.equalsIgnoreCase("title")){
                                channelRss.setTitleChannel(textValue);
                            }else if(tagName.equalsIgnoreCase("link")){
                                channelRss.setLinkChannel(textValue);
                            }else if(tagName.equalsIgnoreCase("description")){
                                channelRss.setDescriptionChannel(textValue);
                            }else if(tagName.equalsIgnoreCase("language")){
                                channelRss.setLanguageChannel(textValue);
                            }else if(tagName.equalsIgnoreCase("managing editor")){
                                channelRss.setManagingEditorChanel(textValue);
                            }else if(tagName.equalsIgnoreCase("generator")){
                                channelRss.setGeneratorChannel(textValue);
                            }else if(tagName.equalsIgnoreCase("pubdate")){
                                channelRss.setPubdateChannel(textValue);
                            }else if(tagName.equalsIgnoreCase("lastbuilddate")){
                                channelRss.setLastbuilddateChannel(textValue);
                            }else if(tagName.equalsIgnoreCase("image")){
                                channelRss.setImageChannel(textValue);
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }

        }catch (Exception e){
            status = false;
            e.printStackTrace();
            Log.d("APPRSSEXAMPLE", " XmlParserRss e="+e.toString());
        }

        /*if(listItemRss != null) {
            for (ItemRss item : listItemRss) {
                Log.d("APPRSSEXAMPLE", "XmlParserRss *****************");
                Log.d("APPRSSEXAMPLE", "XmlParserRss Name: " + item.getTitleItem());
                Log.d("APPRSSEXAMPLE", "XmlParserRss Artists: " + item.getGuidItem());
                Log.d("APPRSSEXAMPLE", "XmlParserRss Release Date: " + item.getLinkItem());
                Log.d("APPRSSEXAMPLE", "XmlParserRss Release Date: " + item.getDescriptionItem());
                Log.d("APPRSSEXAMPLE", "XmlParserRss Release Date: " + item.getPubdateItem());
            }
        }*/

        return true;
    }
}
