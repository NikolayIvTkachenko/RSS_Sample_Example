package com.rsh.nikolay.samplerssexample;

import com.rsh.nikolay.samplerssexample.models.ItemRss;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ItemRssTest {

    String testTitleItem = "Title";
    String testQuidItem = "Guid";
    String testLinkItem = "Link";
    String testDescriptionItem = "Description";
    String testPubdateItem = "PubDate";
    String testDcCreatorItem = "DcCreator";

    String testCategoruPosition1 = "IT";
    String testCategoruPosition2 = "Education";
    String testCategoruPosition3 = "Learning";

    ItemRss itemRss;
    ArrayList<String> categoryItemList;

    @Before
    public void setUp() throws Exception{
        itemRss = new ItemRss();
        categoryItemList = new ArrayList<>();
        categoryItemList.add(testCategoruPosition1);
        categoryItemList.add(testCategoruPosition2);
        categoryItemList.add(testCategoruPosition3);

        itemRss.setTitleItem(testTitleItem);
        itemRss.setLinkItem(testLinkItem);
        itemRss.setGuidItem(testQuidItem);
        itemRss.setDescriptionItem(testDescriptionItem);
        itemRss.setPubdateItem(testPubdateItem);
        itemRss.setDc_creatorItem(testDcCreatorItem);
        itemRss.setCategoryItemList(categoryItemList);

    }

    @Test
    public void checkModelCorrectData(){
        assertEquals (testTitleItem, itemRss.getTitleItem());
        assertEquals (testLinkItem, itemRss.getLinkItem());
        assertEquals (testQuidItem, itemRss.getGuidItem());
        assertEquals (testDescriptionItem, itemRss.getDescriptionItem());
        assertEquals (testPubdateItem, itemRss.getPubdateItem());
        assertEquals (testDcCreatorItem, itemRss.getDc_creatorItem());
        assertThat(categoryItemList, hasItem(testCategoruPosition1));
        assertThat(categoryItemList, hasItem(testCategoruPosition2));
        assertThat(categoryItemList, hasItem(testCategoruPosition3));


    }

    @After
    public void clearData(){
        itemRss = null;
    }
}
