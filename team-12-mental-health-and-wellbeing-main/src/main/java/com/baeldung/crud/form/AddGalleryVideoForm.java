package com.baeldung.crud.form;

public class AddGalleryVideoForm {
    private String galleryVideoPath;
    private String comment;
    private String owner;

    public AddGalleryVideoForm(String galleryVideoPath, String comment, String owner) {
        this.galleryVideoPath = galleryVideoPath;
        this.comment = comment;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
