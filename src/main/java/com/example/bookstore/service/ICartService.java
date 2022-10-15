package com.example.bookstore.service;

import com.example.bookstore.dto.CartDto;
import com.example.bookstore.model.Cart;

import java.util.List;

public interface ICartService {
    public Cart addToCart(Long userid, CartDto cartDto);
    public Cart update(Long cartId,int quantity);
    public List<Cart> retrieve();
    public Cart delete(Long cartId);
    public Cart findById(Long cartId);
    public List<Cart> findByUserid(long userid);
    public Cart increaseQuantity(Long cartId,int quantity);
    public Cart decreaseQuantity(Long cartId,int quantity);
}
