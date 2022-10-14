package com.example.bookstore.service;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookRepo {
    public Book create(BookDto bookDto);
    public List<Book> retrieve();
    public Book update(Long bookId,BookDto bookDto);
    public Book delete(Long bookId);
    public Optional<Book> findById(Long bookId);
    public List<Book> findByDescending(Integer price);
    public List<Book> findByAscending(Integer quantity);
}
