package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.exception.BookstoreException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.Order;
import com.example.bookstore.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService implements IOrderRepo {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ICartService iCartService;
    @Autowired
    IBookRepo iBookRepo;
    public Order placeOrder(OrderDto orderDto) {
        Cart cart = iCartService.findById(orderDto.getCartId());
        int totalPrice=cart.getTotalPrice();
        Order order = new Order(cart, orderDto);
            if (order.getQuantity() <= cart.getQuantity()) {
                int quantity = cart.getQuantity() - order.getQuantity();
                    cart.setQuantity(quantity);
             //       int totalPrice = order.getQuantity() * book.getPrice();
                    order.setPrice(totalPrice);
                    log.info("data placed");
                    return orderRepository.save(order);
                } else
                    throw new BookstoreException("cart has not enough quantity");
    }
    public List<Order> getAllOrders(){
        log.info("got all data");
       return orderRepository.findAll();
    }
    public Order getOrderById(Long orderId){
        log.info("data got by id: "+orderId);
        return orderRepository.findById(orderId).orElseThrow(()->new BookstoreException("this Order ID: "+orderId+" not found"));
    }
    public Order cancelOrder(Long orderId){
        Order order=this.getOrderById(orderId);
        order.setCancel(true);
        log.info("canceled id:"+orderId);
        return orderRepository.save(order);
    }
}