package com.baeldung.crud.DTO;

public class GalleryPhotoMessageDTO {
    private int id;
    private String message;
    private int g_photoID;

    public GalleryPhotoMessageDTO(int id, String message, int g_photoID) {
        this.id = id;
        this.message = message;
        this.g_photoID = g_photoID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
