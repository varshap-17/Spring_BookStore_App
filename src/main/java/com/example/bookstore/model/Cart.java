package com.example.bookstore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Long cartId;
    @JoinColumn(name = "userid")
    @OneToOne(cascade ={CascadeType.ALL})
    private User userid; //foreign key
    @JoinColumn(name = "book_id")
    @ManyToOne(cascade = {CascadeType.ALL})
    private Book bookId;  //foreign key
    private Integer quantity;
    private Integer totalPrice;

    public Cart(User user,Book book,int quantity,int totalPrice){
        this.userid=user;
        this.bookId=book;
        this.quantity=quantity;
        this.totalPrice=totalPrice;
    }
}
