package com.baeldung.crud.repositories;

import com.baeldung.crud.DTO.VideoDTO;
import com.baeldung.crud.form.AddVideoForm;
import com.baeldung.crud.model.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoRepositoryJDBC implements VideoRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public VideoRepositoryJDBC(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;
    }

    @Override
    public List<VideoDTO> findAllVideos() {
        return jdbcTemplate.query(
                "select * from videos order by id desc" ,
                new VideoMapper());
    }

// add cover image, video, and title at the same time
    @Override
    // the name "ByString" because at first this method only has parameter "storePath"
    public boolean addVideoByString(String storePath, String storePathC, AddVideoForm addVideoForm) {
        int rows = jdbcTemplate.update(
                "insert into videos (videoPath, coverPath, videoTitle) values(?,?,?)",
                new Object[]{storePath, storePathC, addVideoForm.getVideoTitle()});
        return rows > 0;
    }

    @Override
    public boolean deleteVideoPathById(int id) {
        int rows = jdbcTemplate.update(
                "delete from videos where id = ?",id);
        return rows>0;
    }

    @Override
    public VideoDTO findVideoById(int id) {
        VideoDTO videoDTO = (VideoDTO) jdbcTemplate.queryForObject(
                "select * from Videos where id=?" ,
                new Object[]{id}, new VideoMapper());
        return videoDTO;
    }

    @Override
    public boolean changeVideoForScaleAById(int id) {
        int rows = jdbcTemplate.update(
                "update videos set flag = 1 where id = ?",id);
        return rows>0;
    }

    @Override
    public List<VideoDTO> findAllScaleAVideos() {
        return jdbcTemplate.query(
                "select * from videos where flag = 1 order by id desc",
                new VideoMapper());
    }

    @Override
    public boolean changeVideoForScaleBById(int id) {
        int rows = jdbcTemplate.update(
                "update videos set flag = 2 where id = ?",id);
        return rows>0;
    }

    @Override
    public List<VideoDTO> findAllScaleBVideos() {
        return jdbcTemplate.query(
                "select * from videos where flag = 2 order by id desc",
                new VideoMapper());
    }

    @Override
    public boolean resetVideoFlagById(int id) {
        int rows = jdbcTemplate.update(
                "update videos set flag = 0 where id = ?",id);
        return rows>0;
    }

}
