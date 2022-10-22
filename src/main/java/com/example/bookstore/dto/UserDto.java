package com.example.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Data
public class UserDto {
    @NotBlank(message = "firstname should not be empty")
    @Pattern(regexp = "^[A-Z]+[A-Za-z]*$",message = "firstname is invalid")
    public String firstname;
    @NotBlank(message = "lastname should not be empty")
    @Pattern(regexp = "^[a-z]*$",message = "lastname is invalid")
    public String lastname;
    @NotBlank(message = "address should not be empty")
    @Pattern(regexp = "^[A-Za-z]*$")
    public String address;
    @NotBlank(message = "email should not be null")
    @Pattern(regexp = "^[a-z]*[0-9]*@[a-z]*(.)[a-z]*",message ="email is invalid")
    public String email;
    @NotBlank(message = "password should not be null")
    public String password;
    public String token;
    public boolean verified;

    public UserDto(String firstname, String lastname, String address, String email, String password){
        this.firstname=firstname;
        this.lastname=lastname;
        this.address=address;
        this.email=email;
        this.password=password;
    }
}
