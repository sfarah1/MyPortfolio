package com.baeldung.crud.model;

import com.baeldung.crud.DTO.GalleryPhotoDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GalleryPhotoMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GalleryPhotoDTO(rs.getInt("id"),
                rs.getString("photoPath"),
                rs.getString("comment"),
                rs.getDate("upTime"),
                rs.getString("owner"),
                rs.getInt("flag"),
                rs.getInt("liked"));
    }
}
