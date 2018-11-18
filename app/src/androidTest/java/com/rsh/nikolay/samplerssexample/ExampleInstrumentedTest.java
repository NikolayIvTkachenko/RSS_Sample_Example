package com.rsh.nikolay.samplerssexample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.rsh.nikolay.samplerssexample.models.ChannelRss;
import com.rsh.nikolay.samplerssexample.models.ItemRss;
import com.rsh.nikolay.samplerssexample.utils.database.RssDbManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    Context appContext;
    ChannelRss channelRss;

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

    String testTitle = "Title";
    String testLink = "http://test.ru";
    String testDescription = "Description";
    String testPubdate = "Pubdate";
    String testGenerator = "Generator";
    String testManagingEdotor = "ManageingEditor";
    String testLastDate = "LastDate";
    String testLanguage = "Language";
    String testImage = "Image";

    @Before
    public void setUp(){
        appContext = InstrumentationRegistry.getTargetContext();

        RssDbManager.getInstance(appContext).removeAllDataRssChannel();
        RssDbManager.getInstance(appContext).removeAllDattaRssItemChannel();

        channelRss = new ChannelRss();
        channelRss.setTitleChannel(testTitle);
        channelRss.setLinkChannel(testLink);
        channelRss.setDescriptionChannel(testDescription);
        channelRss.setPubdateChannel(testPubdate);
        channelRss.setGeneratorChannel(testGenerator);
        channelRss.setManagingEditorChanel(testManagingEdotor);
        channelRss.setLastbuilddateChannel(testLastDate);
        channelRss.setLanguageChannel(testLanguage);
        channelRss.setImageChannel(testImage);

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


        RssDbManager.getInstance(appContext).addChannelRss(channelRss);
        RssDbManager.getInstance(appContext).addItemRssChannel(itemRss);
    }


    @Test
    public void checkReadFromDbChannelRss(){
        ChannelRss channelRssFromDb
                = RssDbManager.getInstance(MainApplication.getInstance().getApplicationContext()).getChannelRss();
        assertEquals (channelRss.getTitleChannel(), channelRssFromDb.getTitleChannel());
        assertEquals (channelRss.getLinkChannel(), channelRssFromDb.getLinkChannel());
        assertEquals (channelRss.getDescriptionChannel(), channelRssFromDb.getDescriptionChannel());
        assertEquals (channelRss.getPubdateChannel(), channelRssFromDb.getPubdateChannel());
        assertEquals (channelRss.getGeneratorChannel(), channelRssFromDb.getGeneratorChannel());
        assertEquals (channelRss.getManagingEditorChanel(), channelRssFromDb.getManagingEditorChanel());
        assertEquals (channelRss.getLastbuilddateChannel(), channelRssFromDb.getLastbuilddateChannel());
        assertEquals (channelRss.getLanguageChannel(), channelRssFromDb.getLanguageChannel());
        assertEquals (channelRss.getImageChannel(), channelRssFromDb.getImageChannel());
    }

    @Test
    public void checkReadFromDbItemRss(){
        ArrayList<ItemRss> listItemsRss
                = RssDbManager.getInstance(MainApplication.getInstance().getApplicationContext()).getListRssItems();
        if(listItemsRss.size()>0) {
            ItemRss itemRssFromDb = listItemsRss.get(0);

            assertEquals(itemRss.getTitleItem(), itemRssFromDb.getTitleItem());
            assertEquals(itemRss.getLinkItem(), itemRssFromDb.getLinkItem());
            assertEquals(itemRss.getGuidItem(), itemRssFromDb.getGuidItem());
            assertEquals(itemRss.getDescriptionItem(), itemRssFromDb.getDescriptionItem());
            assertEquals(itemRss.getPubdateItem(), itemRssFromDb.getPubdateItem());
            assertEquals(itemRss.getDc_creatorItem(), itemRssFromDb.getDc_creatorItem());
            assertThat(itemRss.getCategoryItemList(), hasItem(itemRssFromDb.getCategoryItemList().get(0)));
            assertThat(itemRss.getCategoryItemList(), hasItem(itemRssFromDb.getCategoryItemList().get(1)));
            assertThat(itemRss.getCategoryItemList(), hasItem(itemRssFromDb.getCategoryItemList().get(2)));
        }
    }

    @Test
    public void checkSaveToDbChannelRss(){
        RssDbManager.getInstance(appContext).addChannelRss(channelRss);
    }

    @Test
    public void checkSaveToDbItemRss(){
        RssDbManager.getInstance(appContext).addItemRssChannel(itemRss);
    }

    @Test
    public void removeChannelRssFromDb(){
        RssDbManager.getInstance(appContext).removeAllDataRssChannel();
    }

    @Test
    public void removeItemsRssFromDb(){
        RssDbManager.getInstance(appContext).removeAllDattaRssItemChannel();
    }


    @After
    public void clearData(){
        channelRss = null;
        RssDbManager.getInstance(appContext).removeAllDataRssChannel();

        itemRss=null;
        RssDbManager.getInstance(appContext).removeAllDattaRssItemChannel();
    }
}
