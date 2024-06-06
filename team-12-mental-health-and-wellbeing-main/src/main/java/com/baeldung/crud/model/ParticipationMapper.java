package com.baeldung.crud.model;

import com.baeldung.crud.DTO.ParticipationDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipationMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ParticipationDTO(rs.getInt("participation_id"),
                rs.getString("user_name"),
                rs.getInt("event_id"),
                rs.getDate("participation_date"));
    }
}
