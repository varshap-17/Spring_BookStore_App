package com.example.bookstore.service;

import com.example.bookstore.dto.UserDto;
import com.example.bookstore.dto.LoginDto;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserRepo{
    @Autowired
    UserRepository userRepository;
    public User createData(UserDto userDto){
        User user=new User(userDto);
        log.info("user data saved..");
        return userRepository.save(user);
    }
    public User updateData(Long userid,UserDto userDto){
        User user=userRepository.findById(userid).orElse(null);
        if (user!=null){
            user.setUserid(userid);
            user.setFirstname(userDto.firstname);
            user.setLastname(userDto.lastname);
            user.setAddress(userDto.address);
            user.setEmail(userDto.email);
            user.setPassword(userDto.password);
            log.info("user data updated..");
            return userRepository.save(user);
        }else
            return null;
    }
    public List<User> retrieveData() {
        log.info("user data retrieved!!!");
        return userRepository.findAll();
    }
    public User deleteData(Long userid){
        log.info("user data deleted...");
        userRepository.deleteById(userid);
        return null;
    }
    public Optional<User> findById(Long userid) {
        log.info("user data retrieved by userid!!!");
        return userRepository.findById(userid);
    }
    public List<User> findFirstname(String firstname){
        log.info("user data retrieved by firstname!!!");
        return userRepository.findUserByFirstname(firstname);
    }
    public User login(LoginDto loginDto){
        Optional<User> optionalUser=userRepository.findByEmail(loginDto.getEmail());
        if(loginDto.getEmail().equals(optionalUser.get().getEmail()) && loginDto.getPassword().equals(optionalUser.get().getPassword())){
            log.info("Login Successful...");
            return optionalUser.get();
        }else {
            log.info("Login failed...");
            return new User();
        }
    }
}