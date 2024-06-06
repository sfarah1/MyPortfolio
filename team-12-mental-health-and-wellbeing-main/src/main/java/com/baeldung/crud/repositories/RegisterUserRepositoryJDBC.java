package com.baeldung.crud.repositories;

import com.baeldung.crud.DTO.RegisterUserDTO;
import com.baeldung.crud.form.AddRegisterUserForm;
import com.baeldung.crud.model.RegisterUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//The RegisterUserRepository class extends RegisterUserRepository interface to implement the method in RegisterUserRepository interface
@Repository
public class RegisterUserRepositoryJDBC implements RegisterUserRepository {
    //define a jdbcTemplate
    private JdbcTemplate jdbcTemplate;

    // put the jdbcTemplate into the constructor method
    @Autowired
    public RegisterUserRepositoryJDBC(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;
    }

    @Override
    //Override the addRegister() method
    public boolean addRegisterUser(AddRegisterUserForm addRegisterUserForm) {
        //this method is used to add a registerUser
        int rows = jdbcTemplate.update(//call the jdbcTemplate's update method to add data of a new registerUser
                "insert into registeruser (firstName,lastname,username,password,age,email) values(?,?,?,?,?,?)",
                new Object[]{addRegisterUserForm.getFirstname(), addRegisterUserForm.getLastname(),
                        addRegisterUserForm.getUsername(), addRegisterUserForm.getPassword(),
                        addRegisterUserForm.getAge(), addRegisterUserForm.getEmail()});
        return rows > 0;//return rows>0 means that return a true
    }

    @Override
    //Override the findRegisterUserByUsername method
    public RegisterUserDTO findRegisterUserByUsername(String username) {
        RegisterUserDTO registerUserDTO = (RegisterUserDTO) jdbcTemplate.queryForObject(
                "select password from registeruser where username=?",
                new Object[]{username}, new RegisterUserMapper());
        return registerUserDTO;
    }
    @Override
    //Override the findUsernameAndPassword() method
    //This method is used to validate the username and password that user input whether is true
    public boolean findUsernameAndPassword(String username, String password) {
        String sql = "select * from registeruser where username=? and password=?";
        List<RegisterUserDTO> registerUserDTOS = jdbcTemplate.query(sql, new Object[]{username, password}, new RegisterUserMapper());
        // registerUserDTOS is empty means the username or password is false
        if (registerUserDTOS.isEmpty()) {
            return false;
        }
//        int userId = registerUserDTOS.get(0).getUserID();
        return true;//means that the password and username is true
    }
}

