package com.example.bookstore.model;

import com.example.bookstore.dto.BookDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImage;
    private Integer price;
    private Integer quantity;

    public Book(){

    }
    public Book(Long bookId, BookDto bookDto){
        super();
        this.bookId=bookId;
        this.bookName=bookDto.bookName;
        this.authorName=bookDto.authorName;
        this.bookDescription=bookDto.bookDescription;
        this.bookImage=bookDto.bookImage;
        this.price=bookDto.price;
        this.quantity=bookDto.quantity;
    }
    public Book(BookDto bookDto){
        this.bookName=bookDto.bookName;
        this.authorName=bookDto.authorName;
        this.bookDescription=bookDto.bookDescription;
        this.bookImage=bookDto.bookImage;
        this.price=bookDto.price;
        this.quantity=bookDto.quantity;
    }
}
