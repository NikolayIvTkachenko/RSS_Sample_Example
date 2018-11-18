package com.rsh.nikolay.samplerssexample.models;

/** Created by Tkachenko Nikolay */

public class ChannelRss {

    private String titleChannel;
    private String linkChannel;
    private String descriptionChannel;
    private String languageChannel;
    private String managingEditorChanel;
    private String generatorChannel;
    private String pubdateChannel;
    private String lastbuilddateChannel;
    private String imageChannel;

    public String getTitleChannel() {
        return titleChannel;
    }

    public void setTitleChannel(String titleChannel) {
        this.titleChannel = titleChannel;
    }

    public String getLinkChannel() {
        return linkChannel;
    }

    public void setLinkChannel(String linkChannel) {
        this.linkChannel = linkChannel;
    }

    public String getDescriptionChannel() {
        return descriptionChannel;
    }

    public void setDescriptionChannel(String descriptionChannel) {
        this.descriptionChannel = descriptionChannel;
    }

    public String getLanguageChannel() {
        return languageChannel;
    }

    public void setLanguageChannel(String languageChannel) {
        this.languageChannel = languageChannel;
    }

    public String getManagingEditorChanel() {
        return managingEditorChanel;
    }

    public void setManagingEditorChanel(String managingEditorChanel) {
        this.managingEditorChanel = managingEditorChanel;
    }

    public String getGeneratorChannel() {
        return generatorChannel;
    }

    public void setGeneratorChannel(String generatorChannel) {
        this.generatorChannel = generatorChannel;
    }

    public String getPubdateChannel() {
        return pubdateChannel;
    }

    public void setPubdateChannel(String pubdateChannel) {
        this.pubdateChannel = pubdateChannel;
    }

    public String getLastbuilddateChannel() {
        return lastbuilddateChannel;
    }

    public void setLastbuilddateChannel(String lastbuilddateChannel) {
        this.lastbuilddateChannel = lastbuilddateChannel;
    }

    public String getImageChannel() {
        return imageChannel;
    }

    public void setImageChannel(String imageChannel) {
        this.imageChannel = imageChannel;
    }


    @Override
    public String toString() {
        return "ChannelRss{" +
                "titleChannel='" + titleChannel + '\'' +
                ", linkChannel='" + linkChannel + '\'' +
                ", descriptionChannel='" + descriptionChannel + '\'' +
                ", languageChannel='" + languageChannel + '\'' +
                ", managingEditorChanel='" + managingEditorChanel + '\'' +
                ", generatorChannel='" + generatorChannel + '\'' +
                ", pubdateChannel='" + pubdateChannel + '\'' +
                ", lastbuilddateChannel='" + lastbuilddateChannel + '\'' +
                ", imageChannel='" + imageChannel + '\'' +
                '}';
    }
}
