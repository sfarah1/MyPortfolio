package com.baeldung.crud.repositories;

import com.baeldung.crud.form.AddGalleryPhotoForm;
import com.baeldung.crud.form.AddGalleryPhotoMessageForm;
import com.baeldung.crud.form.AddGalleryVideoForm;
import com.baeldung.crud.form.AddGalleryVideoMessageForm;

public interface GalleryRepository {
// photo part
    public Object findAllPhotoComments();
    public boolean addPhotoByString(String storePath, AddGalleryPhotoForm addGalleryPhotoForm);
    public boolean deletePhotoById(int id);
    public Object findPhotoById(int id);

// video part
    public Object findAllVideoComments();
    public boolean addVideoByString(String storePath, AddGalleryVideoForm addGalleryVideoForm);
    public boolean deleteGalleryVideoById(int id);
    public Object findGalleryVideoById(int id);

// add a flag column that can change value from 0 to 1 (admin give permission)
    public boolean changePhotoPublicStatusById(int id);
    public Object findAllPublicPhotoComments();
    // video part
    public boolean changeVideoPublicStatusById(int id);
    public Object findAllPublicVideoComments();


// add pagination function
    public Object findAllPublicPhotoByPage(int pageNum, int pageRow);
    public int countAllPublicPhoto();
    // video part
    public Object findAllPublicVideoByPage(int pageNum, int pageRow);
    public int countAllPublicVideo();

// add liked function
    public boolean likedPhotoById(int id);
    public boolean likedGalleryVideoById(int id);

// add comments function
    public Object findAllPhotoMessagesByPhotoId(int g_photoID);
    public boolean addPhotoMessage(AddGalleryPhotoMessageForm addGalleryPhotoMessageForm);
    public boolean deletePhotoMessageById(int id);
    public Object findAllPhotoMessage();
    // video part
    public Object findAllVideoMessagesByPhotoId(int g_videoID);
    public boolean addVideoMessage(AddGalleryVideoMessageForm addGalleryVideoMessageForm);
    public boolean deleteVideoMessageById(int id);
    public Object findAllVideoMessage();
}
