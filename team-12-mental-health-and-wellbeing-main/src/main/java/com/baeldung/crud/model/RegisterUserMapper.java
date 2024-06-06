package com.baeldung.crud.model;

import com.baeldung.crud.DTO.RegisterUserDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterUserMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new RegisterUserDTO(rs.getInt("userID"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getInt("age"),
                rs.getString("email"));
    }
}
