package com.baeldung.crud.DTO;

// for resource part
public class VideoDTO {
    private int id;
    private String videoPath;
    private String coverPath;
    private String videoTitle;
    private int flag;

    public VideoDTO(int id, String videoPath, String coverPath, String videoTitle, int flag) {
        this.id = id;
        this.videoPath = videoPath;
        this.coverPath = coverPath;
        this.videoTitle = videoTitle;
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
