package com.example.bookstore.repository;

import com.example.bookstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query(value = "SELECT * FROM cart where userid = :userid", nativeQuery = true)
    List<Cart> findAllCartsByUserId(long userid);

}
