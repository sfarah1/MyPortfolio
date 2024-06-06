package com.baeldung.crud.form;

public class AddGalleryPhotoForm {
    private String photoPath;
    private String comment;
    private String owner;

    public AddGalleryPhotoForm(String photoPath, String comment, String owner) {
        this.photoPath = photoPath;
        this.comment = comment;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
