package com.example.bookstore.model;

import com.example.bookstore.dto.UserDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String password;
    private String token;
    private Boolean verified;
    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(){

    }
    public User(long userid, UserDto userDto){
        super();
        this.userid=userid;
        this.firstname= userDto.firstname;
        this.lastname= userDto.lastname;
        this.address= userDto.address;
        this.email= userDto.email;
        this.password= userDto.password;
    }
    public User(UserDto userDto){
        this.firstname= userDto.firstname;
        this.lastname= userDto.lastname;
        this.address= userDto.address;
        this.email= userDto.email;
        this.password= userDto.password;
    }
}
