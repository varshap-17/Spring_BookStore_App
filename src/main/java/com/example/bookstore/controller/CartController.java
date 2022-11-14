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
    /**
     * Implemented cartSave method by giving userId as path and saving cart data
     * @param userId-passing as path variable
     * @param cartDto-passing as request body
     * @return
     */
    @PostMapping("/postCart/{userId}")
    public ResponseEntity<ResponseDto> cartSave(@PathVariable Long userId,@RequestBody CartDto cartDto){
        Cart cart=null;
        cart=cartService.addToCart(userId,cartDto);
        ResponseDto responseDto=new ResponseDto("data added successfully",cart);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    /**
     *Implemented cartUpdate method by giving cartId and updating quantity
     * @param cartId-passing as path variable
     * @param quantity-passing as param
     * @return
     */
    @PutMapping("/putCart/{cartId}")
    public ResponseEntity<ResponseDto> cartUpdate(@PathVariable Long cartId,@RequestParam int quantity){
        Cart cart=cartService.update(cartId,quantity);
        ResponseDto responseDto=new ResponseDto("data updated cartId: "+cartId+" successfully",cart);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    /**
     *Implemented cartRetrieve method to get all data
     * @return
     */
    @GetMapping("/getCart")
    public List<Cart> cartRetrieve(){
        return cartService.retrieve();
    }
    /**
     *Implemented cartDelete method to delete cart by cartId
     * @param cartId-as path variable
     * @return
     */
    @DeleteMapping("/deleteCart/{cartId}")
    public ResponseEntity<ResponseDto> cartDelete(@PathVariable Long cartId){
        Cart cart=cartService.delete(cartId);
        ResponseDto responseDto=new ResponseDto("data deleted id: "+cartId+" successfully",cart);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    /**
     *Implemented cartById method to get data by cartId
     * @param cartId-as path variable
     * @return
     */
    @GetMapping("/cartById/{cartId}")
    public ResponseEntity<ResponseDto> cartById(@PathVariable Long cartId){
        Cart cart=cartService.findById(cartId);
        ResponseDto responseDto=new ResponseDto("get data by id: "+cartId+" ...",cart);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    /**
     *Implemented findAll method to get all details by giving userid
     * @param userid-as param
     * @return
     */
    @GetMapping("/byUserid/{userid}")
    public List<Cart> findAll(@PathVariable Long userid){
        return cartService.findByUserid(userid);
    }
    /**
     *Implemented incrementQuantity method to increase quantity by cartId
     * @param cartId-as path variable
     * @param quantity-as request param
     * @return
     */
    @PutMapping("/increment/{cartId}")
    public ResponseEntity<ResponseDto> incrementQuantity(@PathVariable Long cartId,@RequestParam int quantity){
        Cart cart=cartService.increaseQuantity(cartId,quantity);
        ResponseDto responseDto=new ResponseDto("cart: "+cartId+" is updated..",cart);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    /**
     *Implemented decrementQuantity method to decreaseQuantity by cartId
     * @param cartId-as path variable
     * @param quantity-as request param
     * @return
     */
    @PutMapping("/decrement/{cartId}")
    public ResponseEntity<ResponseDto> decrementQuantity(@PathVariable Long cartId,@RequestParam int quantity){
        Cart cart=cartService.decreaseQuantity(cartId,quantity);
        ResponseDto responseDto=new ResponseDto("cart: "+cartId+" is updated..",cart);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
