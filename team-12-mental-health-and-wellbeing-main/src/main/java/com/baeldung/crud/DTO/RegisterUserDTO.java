package com.baeldung.crud.DTO;
//Entity class for registered users
public class RegisterUserDTO {
    private int userID;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private int age;
    private String email;
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public RegisterUserDTO(int userID, String firstname, String lastname, String username,
                           String password, int age, String email ){
        this.userID=userID;
        this.firstname=firstname;
        this.lastname=lastname;
        this.username=username;
        this.password=password;
        this.age=age;
        this.email=email;
    }
    public int getUserID(){
        return userID;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int getAge(){
        return age;
    }
    public String getEmail(){
        return email;
    }
}
