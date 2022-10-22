package com.example.bookstore.controller;

import com.example.bookstore.dto.LoginDto;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.dto.UserDto;
import com.example.bookstore.model.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenUtil tokenUtil;
    //example statement
    @GetMapping("/example")
    public String example(@RequestParam String name){
        return name+" table";
    }
    //save method
    @PostMapping("/post")
    public ResponseEntity<ResponseDto> userSave(@Valid @RequestBody UserDto userDto){
        return userService.createAccount(userDto);
    }
    //update method
    @PutMapping("/update/{token}")
    public ResponseEntity<ResponseDto> userUpdate(@Valid @PathVariable String token,@RequestBody UserDto userDto){
        Long tokenId=tokenUtil.decodeToken(token);
        User user=userService.updateData(tokenId,userDto);
        ResponseDto responseDto=new ResponseDto("updated data successfully: "+token,user);
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }
    //retrieve data method
    @GetMapping("/getAll")
    public List<User> userRetrieve(){
        return userService.retrieveData();
    }
    //delete method
    @DeleteMapping("/delete/{token}")
    public ResponseEntity<ResponseDto> userDelete(@PathVariable String token){
        String tokenId= String.valueOf(tokenUtil.decodeToken(token));
        User user=userService.deleteData(Long.valueOf(tokenId));
        ResponseDto responseDto=new ResponseDto("userid: "+tokenId+" is deleted",user);
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }
    //get data by id method
    @GetMapping("/getId/{token}")
    public ResponseEntity<ResponseDto> userById(@PathVariable String token){
        String tokenId= String.valueOf(tokenUtil.decodeToken(token));
        Optional<User> user=userService.findById(Long.valueOf(tokenId));
        ResponseDto responseDto=new ResponseDto("get details by userid: "+tokenId,user);
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }
    //get data by firstname
    @GetMapping("/getName/{firstname}")
    public ResponseEntity<ResponseDto> userByName(@PathVariable String firstname){
        List<User> users=userService.findFirstname(firstname);
        ResponseDto responseDto=new ResponseDto("get details by firstname: "+firstname,users);
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }
    //get data by login method
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> userLogin(@RequestBody LoginDto loginDto){
        Optional<User> user= Optional.ofNullable(userService.login(loginDto));
        ResponseDto responseDto=new ResponseDto("get all details..."+loginDto,user);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    //verify token
    @GetMapping("/verify/{token}")
    public ResponseEntity<ResponseDto> verifyUser(@PathVariable String token){
        ResponseEntity<ResponseDto> user=userService.verify(token);
        ResponseDto responseDto=new ResponseDto("user verified successfully",user);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}