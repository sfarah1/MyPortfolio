package com.baeldung.crud.repositories;

import com.baeldung.crud.DTO.GalleryPhotoDTO;
import com.baeldung.crud.DTO.GalleryPhotoMessageDTO;
import com.baeldung.crud.DTO.GalleryVideoDTO;
import com.baeldung.crud.DTO.GalleryVideoMessageDTO;
import com.baeldung.crud.form.AddGalleryPhotoForm;
import com.baeldung.crud.form.AddGalleryPhotoMessageForm;
import com.baeldung.crud.form.AddGalleryVideoForm;
import com.baeldung.crud.form.AddGalleryVideoMessageForm;
import com.baeldung.crud.model.GalleryPhotoMapper;
import com.baeldung.crud.model.GalleryPhotoMessageMapper;
import com.baeldung.crud.model.GalleryVideoMapper;
import com.baeldung.crud.model.GalleryVideoMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GalleryRepositoryJDBC implements GalleryRepository{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GalleryRepositoryJDBC(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;
    }

// basic database operation for gallery photo part
    @Override
    /* this "Comments" in the method name is the uploaded text article associated with the uploaded photo
       not the comments(messages) that left by other users to this gallery item*/
    public List<GalleryPhotoDTO> findAllPhotoComments() {
        return jdbcTemplate.query(
                "select * from g_photo order by id desc",
                new GalleryPhotoMapper());
    }

    // store photo's path, comment and owner (date is added automatically)
    @Override
    // the name "ByString" because at first this method only has parameter "storePath"
    public boolean addPhotoByString(String storePath, AddGalleryPhotoForm addGalleryPhotoForm) {
        int rows = jdbcTemplate.update(
                "insert into g_photo (photoPath, comment, owner) values(?,?,?)",
                new Object[]{storePath, addGalleryPhotoForm.getComment(), addGalleryPhotoForm.getOwner()});
        return rows > 0;
    }

    @Override
    public boolean deletePhotoById(int id) {
        int rows = jdbcTemplate.update(
                "delete from g_photo where id = ?",id);
        return rows>0;
    }

    @Override
    public GalleryPhotoDTO findPhotoById(int id) {
        GalleryPhotoDTO galleryPhotoDTO = (GalleryPhotoDTO) jdbcTemplate.queryForObject(
                "select * from g_photo where id=?" ,
                new Object[]{id}, new GalleryPhotoMapper());
        return galleryPhotoDTO;
    }

// basic database operation for gallery video part
    @Override
    public List<GalleryVideoDTO> findAllVideoComments() {
        return jdbcTemplate.query(
                "select * from g_video order by id desc",
                new GalleryVideoMapper());
    }

    // store video's path, comment and owner (date is added automatically)
    @Override
    public boolean addVideoByString(String storePath, AddGalleryVideoForm addGalleryVideoForm) {
        int rows = jdbcTemplate.update(
                "insert into g_video (galleryVideoPath, comment, owner) values(?,?,?)",
                new Object[]{storePath, addGalleryVideoForm.getComment(), addGalleryVideoForm.getOwner()});
        return rows > 0;
    }

    @Override
    public boolean deleteGalleryVideoById(int id) {
        int rows = jdbcTemplate.update(
                "delete from g_video where id = ?",id);
        return rows>0;
    }

    @Override
    public GalleryVideoDTO findGalleryVideoById(int id) {
        GalleryVideoDTO galleryvideoDTO = (GalleryVideoDTO) jdbcTemplate.queryForObject(
                "select * from g_video where id=?" ,
                new Object[]{id}, new GalleryVideoMapper());
        return galleryvideoDTO;
    }

// admin give permission to one gallery item and check all items that have the permission
    // photo part
    @Override
    public boolean changePhotoPublicStatusById(int id) {
        int rows = jdbcTemplate.update(
                "update g_photo set flag = 1 where id = ?",id);
        return rows>0;
    }
    @Override
    public List<GalleryPhotoDTO> findAllPublicPhotoComments() {
        return jdbcTemplate.query(
                "select * from g_photo where flag = 1 order by id desc",
                new GalleryPhotoMapper());
    }

    // video part
    @Override
    public boolean changeVideoPublicStatusById(int id) {
        int rows = jdbcTemplate.update(
                "update g_video set flag = 1 where id = ?",id);
        return rows>0;
    }
    @Override
    public List<GalleryVideoDTO> findAllPublicVideoComments() {
        return jdbcTemplate.query(
                "select * from g_video where flag = 1 order by id desc",
                new GalleryVideoMapper());
    }

/* return all public gallery items by using pagination,also need to know the total number
   of public photos and videos respectively for compute in the GalleryController*/
    // photo part
    @Override
    public List<GalleryPhotoDTO> findAllPublicPhotoByPage(int pageNum, int pageRow) {
        int starter = (pageNum - 1) * pageRow;
        return jdbcTemplate.query(
                "select * from g_photo where flag = 1 order by id desc limit " + starter + " , "+ pageRow,
                new GalleryPhotoMapper());
    }
    @Override
    public int countAllPublicPhoto() {
        RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
        jdbcTemplate.query("select * from g_photo where flag = 1",countCallback);
        int count = countCallback.getRowCount();
        return count;
    }

    // video part
    @Override
    public List<GalleryVideoDTO> findAllPublicVideoByPage(int pageNum, int pageRow) {
        int starter = (pageNum - 1) * pageRow;
        return jdbcTemplate.query(
                "select * from g_video where flag = 1 order by id desc limit " + starter + " , "+ pageRow,
                new GalleryVideoMapper());
    }
    @Override
    public int countAllPublicVideo() {
        RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
        jdbcTemplate.query("select * from g_video where flag = 1",countCallback);
        int count = countCallback.getRowCount();
        return count;
    }

// add liked function, the value of "liked" plus one
    // photo part
    @Override
    public boolean likedPhotoById(int id) {
        int rows = jdbcTemplate.update(
                "update g_photo set liked = liked + 1 where id = ?",id);
        return rows>0;
    }
    // video part
    @Override
    public boolean likedGalleryVideoById(int id) {
        int rows = jdbcTemplate.update(
                "update g_video set liked = liked + 1 where id = ?",id);
        return rows>0;
    }

/* add comments function, allow user to find all comments left by others to one gallery item,
   also allow public user to make comment on it. admin can check and delete each comments */
    // photo part
    @Override
    public List<GalleryPhotoMessageDTO> findAllPhotoMessagesByPhotoId(int g_photoID) {
        return jdbcTemplate.query(
                "select * from g_pmessage where g_photoID = ? order by id desc",
                new Object[]{g_photoID}, new GalleryPhotoMessageMapper());
    }
    @Override
    public boolean addPhotoMessage(AddGalleryPhotoMessageForm addGalleryPhotoMessageForm) {
        int rows = jdbcTemplate.update(
                "insert into g_pmessage (message, g_photoID) values(?,?)",
                new Object[]{addGalleryPhotoMessageForm.getMessage(), addGalleryPhotoMessageForm.getG_photoID()});
        return rows > 0;
    }
    @Override
    public boolean deletePhotoMessageById(int id) {
        int rows = jdbcTemplate.update(
                "delete from g_pmessage where id = ?",id);
        return rows>0;
    }
    @Override
    public List<GalleryPhotoMessageDTO> findAllPhotoMessage() { // used for admin to check and delete
        return jdbcTemplate.query(
                "select * from g_pmessage order by id desc",
                new GalleryPhotoMessageMapper());
    }

    // video part
    @Override
    public List<GalleryVideoMessageDTO> findAllVideoMessagesByPhotoId(int g_videoID) {
        return jdbcTemplate.query(
                "select * from g_vmessage where g_videoID = ? order by id desc",
                new Object[]{g_videoID}, new GalleryVideoMessageMapper());
    }

    @Override
    public boolean addVideoMessage(AddGalleryVideoMessageForm addGalleryVideoMessageForm) {
        int rows = jdbcTemplate.update(
                "insert into g_vmessage (message, g_videoID) values(?,?)",
                new Object[]{addGalleryVideoMessageForm.getMessage(), addGalleryVideoMessageForm.getG_videoID()});
        return rows > 0;
    }

    @Override
    public boolean deleteVideoMessageById(int id) {
        int rows = jdbcTemplate.update(
                "delete from g_vmessage where id = ?",id);
        return rows>0;
    }

    @Override
    public List<GalleryVideoMessageDTO> findAllVideoMessage() {
        return jdbcTemplate.query(
                "select * from g_vmessage order by id desc",
                new GalleryVideoMessageMapper());
    }

}
