package com.example.bookstore.service;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService implements IBookRepo{
    @Autowired
    BookRepository bookRepository;
    public Book create(BookDto bookDto){
        Book book=new Book(bookDto);
        log.info("book data saved..");
        return bookRepository.save(book);
    }
    public List<Book> retrieve(){
        log.info("book data retrieved..");
        return bookRepository.findAll();
    }
    public Book update(Long bookId,BookDto bookDto){
        Book book=bookRepository.findById(bookId).orElse(null);
        if(book!=null){
            book.setBookName(bookDto.bookName);
            book.setAuthorName(bookDto.authorName);
            book.setBookDescription(bookDto.bookDescription);
            book.setBookImage(bookDto.bookImage);
            book.setPrice(bookDto.price);
            book.setQuantity(bookDto.quantity);
            log.info("book data updated..");
            return bookRepository.save(book);
        }else {
            return null;
        }
    }
    public Book delete(Long bookId){
        log.info("book data deleted");
        bookRepository.deleteById(bookId);
        return null;
    }
    public Optional<Book> findById(Long bookId){
        log.info("book data by id");
        return bookRepository.findById(bookId);
    }
    public List<Book> findByDescending(Integer price){
        log.info("descending order");
        return bookRepository.findByPrice(price);
    }
    public List<Book> findByAscending(Integer quantity){
        log.info("ascending order");
        return bookRepository.findBooksByQuantity(quantity);
    }
}
