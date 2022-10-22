package com.example.bookstore.service;

import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.dto.UserDto;
import com.example.bookstore.dto.LoginDto;
import com.example.bookstore.model.Email;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserRepo{
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    TokenUtil tokenUtil;
    public ResponseEntity<ResponseDto> createAccount(UserDto userDto){
        User user=userRepository.save(new User(userDto));
        String token=tokenUtil.createToken(user.getUserid());
        user.setToken(token);
        Email email=new Email(user.getEmail(),"user is registered",user.getFirstname()+"=>"+emailService.getLink(token));
        try {
            emailService.sendEmail(email);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        userRepository.save(user);
        log.info("user data saved..");
        ResponseDto responseDto=new ResponseDto("user is created",user,token);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
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
    public ResponseEntity<ResponseDto> verify(String token) {
        Optional<User> user=userRepository.findById(Long.valueOf(Math.toIntExact(tokenUtil.decodeToken(token))));
        if (user.isEmpty()) {
            ResponseDto responseDTO = new ResponseDto("ERROR: Invalid token", null, token);
            return new ResponseEntity<ResponseDto>(responseDTO, HttpStatus.UNAUTHORIZED);
        }
        user.get().setVerified(true);
        userRepository.save(user.get());
        ResponseDto responseDTO = new ResponseDto(" The user has been verified ", user, token);
        return new ResponseEntity<ResponseDto>(responseDTO, HttpStatus.OK);
    }
}