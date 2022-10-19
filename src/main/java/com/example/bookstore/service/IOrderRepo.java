package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.model.Order;

import java.util.List;

public interface IOrderRepo {
    public Order placeOrder(OrderDto orderDto);
    public List<Order> getAllOrders();
    public Order getOrderById(Long orderId);
    public Order cancelOrder(Long orderId);
}
