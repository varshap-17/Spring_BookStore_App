package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    /**
     * example method
     * @param cls-request param
     * @return
     */
    @GetMapping("/example")
    public String example(@RequestParam String cls){
        return "this is "+cls+" table";
    }
    /**
     *Implemented bookSave method to add the book details and save them in database
     * @param bookDto-passing as request body
     * @return
     */
    @PostMapping("/postData")
    public ResponseEntity<ResponseDto> bookSave(@RequestBody BookDto bookDto){
        Book book=null;
        book=bookService.create(bookDto);
        ResponseDto responseDto=new ResponseDto("data saved successfully",book);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    /**
     *Implemented bookRetrieve method to retrieve all details
     * @return
     */
    @GetMapping("/getData")
    public List<Book> bookRetrieve(){
        return bookService.retrieve();
    }
    /**
     *Implemented bookUpdate method to update them by bookId
     * @param bookId-passing as path variable
     * @param bookDto-passing as request body
     * @return
     */
    @PutMapping("/putData/{bookId}")
    public ResponseEntity<ResponseDto> bookUpdate(@PathVariable Long bookId,@RequestBody BookDto bookDto){
        Book book=bookService.update(bookId,bookDto);
        ResponseDto responseDto=new ResponseDto("book data updated id: "+bookId+" successfully",book);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    /**
     *Implemented bookDelete method by deleting bookId
     * @param bookId-passing as path variable
     * @return
     */
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDto> bookDelete(@PathVariable Long bookId){
        Book book=bookService.delete(bookId);
        ResponseDto responseDto=new ResponseDto("book data deleted id: "+bookId+" successfully",book);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    /**
     *Implemented bookById method to get details by bookId
     * @param bookId
     * @return
     */
    @GetMapping("/byId/{bookId}")
    public ResponseEntity<ResponseDto> bookById(@PathVariable Long bookId){
        Book book=bookService.findById(bookId);
        ResponseDto responseDto=new ResponseDto("get data by id: "+bookId+" successfully",book);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    /**
     *Implemented bookHighToLow method to get data by price in descending order(high to low)
     * @param price
     * @return
     */
    @GetMapping("/byPrice")
    public List<Book> bookHighToLow(Integer price){
        return bookService.findByDescending(price);
    }

    /**
     * Implemented booksLowToHigh method to get data by quantity in ascending order(low to high)
     * @param quantity
     * @return
     */
    @GetMapping("/byQuantity")
    public List<Book> booksLowToHigh(Integer quantity){
        return bookService.findByAscending(quantity);
    }
}