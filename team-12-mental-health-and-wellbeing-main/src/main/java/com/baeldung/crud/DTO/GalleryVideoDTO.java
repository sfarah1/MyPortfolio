package com.baeldung.crud.DTO;

import java.sql.Date;

public class GalleryVideoDTO {
    private int id;
    private String galleryVideoPath;
    private String comment;
    private Date upTime;
    private String owner;
    private int flag;
    private int liked;

    public GalleryVideoDTO(int id, String galleryVideoPath, String comment, Date upTime, String owner, int flag, int liked) {
        this.id = id;
        this.galleryVideoPath = galleryVideoPath;
        this.comment = comment;
        this.upTime = upTime;
        this.owner = owner;
        this.flag = flag;
        this.liked = liked;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGalleryVideoPath() {
        return galleryVideoPath;
    }

    public void setGalleryVideoPath(String galleryVideoPath) {
        this.galleryVideoPath = galleryVideoPath;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
