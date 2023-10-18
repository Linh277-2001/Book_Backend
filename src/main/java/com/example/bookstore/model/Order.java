package com.example.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String orderDescription;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;

    private int user_id;
    private String address;
    private String phone;
    private String created;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = OrderDetail.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderDetail> cartItems;


//    public Order(String orderDescription, User user, List<OrderDetail> cartItems) {
//        this.orderDescription = orderDescription;
//        this.user = user;
//        this.cartItems = cartItems;
//    }

    public Order(String orderDescription, Integer id, List<OrderDetail> cartItems, String address,String phone, String created) {
        this.orderDescription = orderDescription;
        this.user_id= id;
        this.cartItems = cartItems;
        this.address=address;
        this.phone=phone;
        this.created=created;
    }


}
