package com.baeldung.crud.repositories;

import com.baeldung.crud.form.AddVideoForm;

// this is for resource part
public interface VideoRepository {
    public Object findAllVideos();
    public boolean addVideoByString(String storePath, String storePathC, AddVideoForm addVideoForm);
    public boolean deleteVideoPathById(int id);
    public Object findVideoById(int id);


    // add a flag column that can change value from 0 to 1 or 2

    // flag value = 1 means video is recommended to scale A
    public boolean changeVideoForScaleAById(int id);
    public Object findAllScaleAVideos();

    // flag value = 2 means video is recommended to scale A
    public boolean changeVideoForScaleBById(int id);
    public Object findAllScaleBVideos();

    // reset flag value to 0
    public boolean resetVideoFlagById(int id);
}
