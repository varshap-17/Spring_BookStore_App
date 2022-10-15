package com.example.bookstore.controller;

import com.example.bookstore.dto.CartDto;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.model.Cart;
import com.example.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    //add to cart data saved
    @PostMapping("/postCart/{userId}")
    public ResponseEntity<ResponseDto> cartSave(@PathVariable Long userId,@RequestBody CartDto cartDto){
        Cart cart=null;
        cart=cartService.addToCart(userId,cartDto);
        ResponseDto responseDto=new ResponseDto("data added successfully",cart);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    //update data
    @PutMapping("/putCart/{cartId}")
    public ResponseEntity<ResponseDto> cartUpdate(@PathVariable Long cartId,@RequestParam int quantity){
        Cart cart=cartService.update(cartId,quantity);
        ResponseDto responseDto=new ResponseDto("data updated cartId: "+cartId+" successfully",cart);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    //retrieve data
    @GetMapping("/getCart")
    public List<Cart> cartRetrieve(){
        return cartService.retrieve();
    }
    //delete method
    @DeleteMapping("/deleteCart/{cartId}")
    public ResponseEntity<ResponseDto> cartDelete(@PathVariable Long cartId){
        Cart cart=cartService.delete(cartId);
        ResponseDto responseDto=new ResponseDto("data deleted id: "+cartId+" successfully",cart);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    //cart by id method
    @GetMapping("/cartById/{cartId}")
    public ResponseEntity<ResponseDto> cartById(@PathVariable Long cartId){
        Cart cart=cartService.findById(cartId);
        ResponseDto responseDto=new ResponseDto("get data by id: "+cartId+" ...",cart);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    //find userid in cart table
    @GetMapping("/byUserid/{userid}")
    public List<Cart> findAll(@PathVariable Long userid){
        return cartService.findByUserid(userid);
    }
    //increasing the quantity
    @PutMapping("/increment/{cartId}")
    public ResponseEntity<ResponseDto> incrementQuantity(@PathVariable Long cartId,@RequestParam int quantity){
        Cart cart=cartService.increaseQuantity(cartId,quantity);
        ResponseDto responseDto=new ResponseDto("cart: "+cartId+" is updated..",cart);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    //decreasing the quantity
    @PutMapping("/decrement/{cartId}")
    public ResponseEntity<ResponseDto> decrementQuantity(@PathVariable Long cartId,@RequestParam int quantity){
        Cart cart=cartService.decreaseQuantity(cartId,quantity);
        ResponseDto responseDto=new ResponseDto("cart: "+cartId+" is updated..",cart);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
