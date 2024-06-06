package com.baeldung.crud.DTO;

import java.sql.Date;

public class GalleryPhotoDTO {
    private int id;
    private String photoPath;
    private String comment;
    private Date upTime;
    private String owner;
    private int flag;
    private int liked;

    public GalleryPhotoDTO(int id, String photoPath, String comment, Date upTime, String owner, int flag, int liked) {
        this.id = id;
        this.photoPath = photoPath;
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
