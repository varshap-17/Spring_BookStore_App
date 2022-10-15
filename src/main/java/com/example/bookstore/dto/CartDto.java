package com.example.bookstore.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CartDto {
    public Long bookId;
    public int quantity;
}
