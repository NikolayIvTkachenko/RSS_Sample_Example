package com.rsh.nikolay.samplerssexample;

import com.rsh.nikolay.samplerssexample.models.ChannelRss;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Created by Tkachenko Nikolay */
public class ChannelRusTest {
    String testTitle = "Title";
    String testLink = "http://test.ru";
    String testDescription = "Description";
    String testPubdate = "Pubdate";
    String testGenerator = "Generator";
    String testManagingEdotor = "ManageingEditor";
    String testLastDate = "LastDate";
    String testLanguage = "Language";
    String testImage = "Image";

    ChannelRss channelRss;


    @Before
    public void setUp() throws Exception{
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

    }

    @Test
    public void checkModelCorrectData(){
        assertEquals (testTitle, channelRss.getTitleChannel());
        assertEquals (testLink, channelRss.getLinkChannel());
        assertEquals (testDescription, channelRss.getDescriptionChannel());
        assertEquals (testPubdate, channelRss.getPubdateChannel());
        assertEquals (testGenerator, channelRss.getGeneratorChannel());
        assertEquals (testManagingEdotor, channelRss.getManagingEditorChanel());
        assertEquals (testLastDate, channelRss.getLastbuilddateChannel());
        assertEquals (testLanguage, channelRss.getLanguageChannel());
        assertEquals (testImage, channelRss.getImageChannel());

    }




    @After
    public void clearData(){
        channelRss = null;
    }

}
