package com.baeldung.crud.form;

public class AddGalleryPhotoMessageForm {
    private String message;
    private int g_photoID;

    public AddGalleryPhotoMessageForm(String message, int g_photoID) {
        this.message = message;
        this.g_photoID = g_photoID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getG_photoID() {
        return g_photoID;
    }

    public void setG_photoID(int g_photoID) {
        this.g_photoID = g_photoID;
    }
}
