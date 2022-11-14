package com.example.bookstore.model;

import com.example.bookstore.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "order_table")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate orderDate=LocalDate.now();
    private Integer price;
    private boolean cancel;
    private Integer quantity;
    private String address;
    @JoinColumn(name = "cart_id")
    @ManyToOne(fetch = FetchType.LAZY)
    public Cart cartId;
    public Order(Cart cart, OrderDto orderDto){
        this.cartId= cart;
        orderData(orderDto);
    }
    public void orderData(OrderDto orderDto){
        this.address=orderDto.address;
        this.quantity=orderDto.quantity;
        this.orderDate=getOrderDate();
        this.price=getPrice();
    }
}
