package com.example.bookstore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class BookDto {
    @NotBlank(message = "bookName should not be null")
    @Pattern(regexp = "^[A-Za-z]*$",message = "invalid bookName")
    public String bookName;
    @NotBlank(message = "authorName should not be null")
    @Pattern(regexp = "^[A-Za-z]*$",message = "given author name is invalid")
    public String authorName;
    @NotBlank(message = "bookDescription should not be null")
    @Pattern(regexp = "^[A-Za-z]*$",message = "given description is invalid")
    public String bookDescription;
    @NotBlank(message = "bookImage should not be empty")
    public String bookImage;

    public Integer price;

    public Integer quantity;

    public BookDto(String bookName,String authorName,String bookDescription,String bookImage,Integer price,Integer quantity){
        this.bookName=bookName;
        this.authorName=authorName;
        this.bookDescription=bookDescription;
        this.bookImage=bookImage;
        this.price=price;
        this.quantity=quantity;
    }
}
