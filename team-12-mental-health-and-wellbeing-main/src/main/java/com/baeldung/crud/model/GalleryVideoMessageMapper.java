package com.baeldung.crud.model;

import com.baeldung.crud.DTO.GalleryVideoMessageDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GalleryVideoMessageMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GalleryVideoMessageDTO(rs.getInt("id"),
                rs.getString("message"),
                rs.getInt("g_videoID"));
    }
}
