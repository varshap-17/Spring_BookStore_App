package com.example.bookstore.service;

import com.example.bookstore.dto.CartDto;
import com.example.bookstore.exception.BookstoreException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements ICartService{
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    public Cart addToCart(Long userid,CartDto cartDto){
        Optional<User> userdata=userRepository.findById(userid);
        Optional<Book> book=bookRepository.findById(cartDto.getBookId());
        if(userdata.isPresent()){
            if(book.isPresent()){
                    log.info("Data saved..");
                    int totalPrice=book.get().getPrice()*cartDto.quantity;
                    Cart cartDetails=new Cart(userdata.get(),book.get(),cartDto.getQuantity(),totalPrice);
                    return cartRepository.save(cartDetails);
                }else
                    throw new BookstoreException("Book does not exist invalid book id");
            }else
                throw new BookstoreException("User does not exist invalid user id");
        }

    public Cart update(Long cartId,int quantity){
        Cart cart=cartRepository.findById(cartId).orElse(null);
        if(cart!=null){
            cart.setQuantity(quantity);
            log.info("updated data..");
            return cartRepository.save(cart);
        }else
            return null;
    }
    public List<Cart> retrieve(){
        log.info("cart data retrieved..");
        return cartRepository.findAll();
    }
    public Cart delete(Long cartId){
       Cart cart=this.findById(cartId);
       cartRepository.deleteById(cartId);
       return cart;
    }
    public Cart findById(Long cartId){
        log.info("data retrieved by id.."+cartId);
        return cartRepository.findById(cartId).orElse(null);
    }
    public List<Cart> findByUserid(long userid){
        return cartRepository.findAllCartsByUserId(userid);
    }
    public Cart increaseQuantity(Long cartId,int quantity){
        Optional<Cart> cart=cartRepository.findById(cartId);
        CartDto cartDto=new CartDto();
        Optional<Book> book=bookRepository.findById(cart.get().getBookId().getBookId());
        if (quantity>=cartDto.quantity){
            int totalPrice=book.get().getPrice()*cart.get().getQuantity();
            cart.get().setQuantity(quantity);
            cart.get().setTotalPrice(totalPrice);
            return cartRepository.save(cart.get());
        }else
            throw new BookstoreException("add more books");
    }
    public Cart decreaseQuantity(Long cartId,int quantity){
        Optional<Cart> cart=cartRepository.findById(cartId);
        CartDto cartDto=new CartDto();
        Optional<Book> book=bookRepository.findById(cart.get().getBookId().getBookId());
        if (quantity>=cartDto.quantity){
            int totalPrice=book.get().getPrice()*cart.get().getQuantity();
            cart.get().setQuantity(quantity);
            cart.get().setTotalPrice(totalPrice);
            return cartRepository.save(cart.get());
        }else
            throw new BookstoreException("add less books");
    }
}
