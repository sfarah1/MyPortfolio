package com.baeldung.crud.model;

import com.baeldung.crud.DTO.GalleryVideoDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GalleryVideoMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GalleryVideoDTO(rs.getInt("id"),
                rs.getString("galleryVideoPath"),
                rs.getString("comment"),
                rs.getDate("upTime"),
                rs.getString("owner"),
                rs.getInt("flag"),
                rs.getInt("liked"));
    }
}
