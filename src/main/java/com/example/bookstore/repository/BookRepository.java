package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query(value = "select * from book order by price desc",nativeQuery = true)
    List<Book> findByPrice(Integer price);
    @Query(value = "select * from book order by quantity asc",nativeQuery = true)
    List<Book> findBooksByQuantity(Integer quantity);
}
