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
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    //example statement
    @GetMapping("/example")
    public String example(@RequestParam String cls){
        return "this is "+cls+" table";
    }
    //save method
    @PostMapping("/postData")
    public ResponseEntity<ResponseDto> bookSave(@RequestBody BookDto bookDto){
        Book book=null;
        book=bookService.create(bookDto);
        ResponseDto responseDto=new ResponseDto("data saved successfully",book);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    //retrieve data method
    @GetMapping("/getData")
    public List<Book> bookRetrieve(){
        return bookService.retrieve();
    }
    //update method
    @PutMapping("/putData/{bookId}")
    public ResponseEntity<ResponseDto> bookUpdate(@PathVariable Long bookId,@RequestBody BookDto bookDto){
        Book book=bookService.update(bookId,bookDto);
        ResponseDto responseDto=new ResponseDto("book data updated id: "+bookId+" successfully",book);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    //delete method
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDto> bookDelete(@PathVariable Long bookId){
        Book book=bookService.delete(bookId);
        ResponseDto responseDto=new ResponseDto("book data deleted id: "+bookId+" successfully",book);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    //find data by id method
    @GetMapping("/byId/{bookId}")
    public ResponseEntity<ResponseDto> bookById(@PathVariable Long bookId){
        Optional<Book> book=bookService.findById(bookId);
        ResponseDto responseDto=new ResponseDto("get data by id: "+bookId+" successfully",book);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    //find data by price in descending order(high to low)
    @GetMapping("/byPrice")
    public List<Book> bookHighToLow(Integer price){
        return bookService.findByDescending(price);
    }
    //find data by quantity in ascending order(low to high)
    @GetMapping("/byQuantity")
    public List<Book> booksLowToHigh(Integer quantity){
        return bookService.findByAscending(quantity);
    }
}