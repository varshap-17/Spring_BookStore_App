package com.example.bookstore.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component
public class OrderDto {
    public Long cartId;
    public String address;
    public int quantity;
}
