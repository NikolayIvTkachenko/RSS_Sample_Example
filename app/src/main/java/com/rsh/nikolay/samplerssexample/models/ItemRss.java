package com.rsh.nikolay.samplerssexample.models;

/** Created by Tkachenko Nikolay */

import java.util.ArrayList;

public class ItemRss {

    private String titleItem;
    private String guidItem;
    private String linkItem;
    private String descriptionItem;
    private String pubdateItem;
    private String dc_creatorItem;
    private ArrayList<String> categoryItemList;


    public ItemRss(){
        categoryItemList = new ArrayList<>();
    }

    public String getTitleItem() {
        return titleItem;
    }

    public void setTitleItem(String titleItem) {
        this.titleItem = titleItem;
    }

    public String getGuidItem() {
        return guidItem;
    }

    public void setGuidItem(String guidItem) {
        this.guidItem = guidItem;
    }

    public String getLinkItem() {
        return linkItem;
    }

    public void setLinkItem(String linkItem) {
        this.linkItem = linkItem;
    }

    public String getDescriptionItem() {
        return descriptionItem;
    }

    public void setDescriptionItem(String descriptionItem) {
        this.descriptionItem = descriptionItem;
    }

    public String getPubdateItem() {
        return pubdateItem;
    }

    public void setPubdateItem(String pubdateItem) {
        this.pubdateItem = pubdateItem;
    }

    public String getDc_creatorItem() {
        return dc_creatorItem;
    }

    public void setDc_creatorItem(String dc_creatorItem) {
        this.dc_creatorItem = dc_creatorItem;
    }

    public void setItemCategoryItemList(String category){
        this.categoryItemList.add(category);
    }

    public ArrayList<String> getCategoryItemList() {
        return categoryItemList;
    }

    public void setCategoryItemList(ArrayList<String> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }



    @Override
    public String toString() {
        String result = "";
        result =  "Title: " + titleItem + "\n" +
                  "Guid: " + guidItem + "\n" +
                  "Link: " + linkItem + "\n"+
                  "Description: " + descriptionItem + "\n"+
                  "PubDate: " + pubdateItem + "\n"+
                  "Creator: " + dc_creatorItem + "\n";
        if(categoryItemList!=null && categoryItemList.size()>0) {
            for (String category : categoryItemList) {
                result += "Category: " + category + "\n";
            }
        }

        return result;
    }
}
