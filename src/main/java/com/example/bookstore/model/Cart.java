package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Data
@NoArgsConstructor
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @org.hibernate.annotations.ForeignKey(name = "none")
    private Long cartId;
    @JoinColumn(name = "userid")
    @OneToOne(fetch = FetchType.LAZY)
    private User userid; //foreign key
    @JoinColumn(name = "book_id")
    @ManyToOne(fetch = FetchType.LAZY)
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
