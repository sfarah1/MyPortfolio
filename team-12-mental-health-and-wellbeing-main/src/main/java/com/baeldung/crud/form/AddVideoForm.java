package com.baeldung.crud.form;

public class AddVideoForm {

    private String videoTitle;

    public AddVideoForm(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}
