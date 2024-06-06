package com.baeldung.crud.model;

import com.baeldung.crud.DTO.EventDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new EventDTO(rs.getInt("event_id"),
                rs.getString("event_name"),
                rs.getString("event_content"),
                rs.getDate("startDate"),
                rs.getDate("endDate"),
                rs.getString("imgpath"));
    }
}
