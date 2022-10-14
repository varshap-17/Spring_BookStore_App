package com.example.bookstore.dto;

import lombok.Data;

@Data
public class LoginDto {
    public String email;
    public String password;
    public LoginDto(String email, String password){
        this.email=email;
        this.password=password;
    }
}
