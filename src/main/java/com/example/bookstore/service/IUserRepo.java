package com.example.bookstore.service;

import com.example.bookstore.dto.UserDto;
import com.example.bookstore.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepo {
    public User createData(UserDto userDto);
    public User updateData(Long userid,UserDto userDto);
    public List<User> retrieveData();
    public User deleteData(Long userid);
    public Optional<User> findById(Long userid);
    public List<User> findFirstname(String firstname);
}
