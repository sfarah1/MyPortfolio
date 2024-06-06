package com.baeldung.crud.model;

import com.baeldung.crud.DTO.VideoDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new VideoDTO(rs.getInt("id"),
                  rs.getString("videoPath"),
                  rs.getString("coverPath"),
                  rs.getString("videoTitle"),
                  rs.getInt("flag"));
    }

}