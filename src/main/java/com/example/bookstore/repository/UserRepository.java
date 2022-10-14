package com.example.bookstore.repository;

import com.example.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from user where firstname=:firstname",nativeQuery = true)
    List<User> findUserByFirstname(String firstname);
    @Query(value = "select * from user where email=:email",nativeQuery = true)
    Optional<User> findByEmail(String email);

}
