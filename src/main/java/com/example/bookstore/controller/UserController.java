package com.example.bookstore.controller;

import com.example.bookstore.dto.LoginDto;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.dto.UserDto;
import com.example.bookstore.model.User;
import com.example.bookstore.service.UserService;
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
    //example statement
    @GetMapping("/example")
    public String example(@RequestParam String name){
        return name+" table";
    }
    //save method
    @PostMapping("/post")
    public ResponseEntity<ResponseDto> userSave(@Valid @RequestBody UserDto userDto){
        User user=null;
        user=userService.createData(userDto);
        ResponseDto responseDto=new ResponseDto("saved data successfully",user);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
    //update method
    @PutMapping("/update/{userid}")
    public ResponseEntity<ResponseDto> userUpdate(@Valid @PathVariable Long userid,@RequestBody UserDto userDto){
        User user=userService.updateData(userid,userDto);
        ResponseDto responseDto=new ResponseDto("updated data successfully",user);
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }
    //retrieve data method
    @GetMapping("/getAll")
    public List<User> userRetrieve(){
        return userService.retrieveData();
    }
    //delete method
    @DeleteMapping("/delete/{userid}")
    public ResponseEntity<ResponseDto> userDelete(@PathVariable Long userid){
        User user=userService.deleteData(userid);
        ResponseDto responseDto=new ResponseDto("userid: "+userid+" is deleted",user);
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }
    //get data by id method
    @GetMapping("/getId/{userid}")
    public ResponseEntity<ResponseDto> userById(@PathVariable Long userid){
        Optional<User> user=userService.findById(userid);
        ResponseDto responseDto=new ResponseDto("get details by userid: "+userid,user);
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
}