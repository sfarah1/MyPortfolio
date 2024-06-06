package com.baeldung.crud.form;

public class AddGalleryVideoMessageForm {
    private String message;
    private int g_videoID;

    public AddGalleryVideoMessageForm(String message, int g_videoID) {
        this.message = message;
        this.g_videoID = g_videoID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getG_videoID() {
        return g_videoID;
    }

    public void setG_videoID(int g_videoID) {
        this.g_videoID = g_videoID;
    }
}
