package com.example.bookstore.service;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.Book;

import java.util.List;

public interface IBookRepo {
    public Book create(BookDto bookDto);
    public List<Book> retrieve();
    public Book update(Long bookId,BookDto bookDto);
    public Book delete(Long bookId);
    public Book findById(Long bookId);
    public List<Book> findByDescending(Integer price);
    public List<Book> findByAscending(Integer quantity);
}
