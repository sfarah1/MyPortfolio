package com.baeldung.crud.model;

import com.baeldung.crud.DTO.GalleryPhotoMessageDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GalleryPhotoMessageMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GalleryPhotoMessageDTO(rs.getInt("id"),
                rs.getString("message"),
                rs.getInt("g_photoID"));
    }
}
