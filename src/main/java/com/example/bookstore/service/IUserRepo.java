package com.example.bookstore.service;

import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.dto.UserDto;
import com.example.bookstore.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserRepo {
    public ResponseEntity<ResponseDto> createAccount(UserDto userDto);
    public User updateData(Long userid,UserDto userDto);
    public List<User> retrieveData();
    public User deleteData(Long userid);
    public Optional<User> findById(Long userid);
    public List<User> findFirstname(String firstname);
    public ResponseEntity<ResponseDto> verify(String token);
}
