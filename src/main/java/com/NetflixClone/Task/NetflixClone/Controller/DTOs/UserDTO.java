package com.NetflixClone.Task.NetflixClone.Controller.DTOs;

import java.util.Date;

public class UserDTO {
    Long id ;
    String firstName; ;
    String lastName; ;
    String email; ;
    Date dateOfBirth; ;

    public UserDTO(String firstName, String lastName, String email, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
