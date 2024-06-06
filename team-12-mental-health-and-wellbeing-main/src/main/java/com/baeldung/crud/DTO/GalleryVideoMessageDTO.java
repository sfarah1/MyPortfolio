package com.baeldung.crud.DTO;

public class GalleryVideoMessageDTO {
    private int id;
    private String message;
    private int g_videoID;

    public GalleryVideoMessageDTO(int id, String message, int g_videoID) {
        this.id = id;
        this.message = message;
        this.g_videoID = g_videoID;
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

    public int getG_videoID() {
        return g_videoID;
    }

    public void setG_videoID(int g_videoID) {
        this.g_videoID = g_videoID;
    }
}
