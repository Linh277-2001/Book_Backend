package com.example.bookstore.dto;

import com.example.bookstore.model.OrderDetail;
import lombok.Data;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private String orderDescription;
    private List<OrderDetail> cartItems;
    private String userEmail;
    private String userName;

    private String address;
    private String phone;
    private String created;

    public OrderDTO() {
    }

    public OrderDTO(String orderDescription, List<OrderDetail> cartItems, String userEmail, String userName, String address, String phone,String created) {
        this.orderDescription = orderDescription;
        this.cartItems = cartItems;
        this.userEmail = userEmail;
        this.userName = userName;
        this.address= address;
        this.phone=phone;
        this.created=created;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public List<OrderDetail> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<OrderDetail> cartItems) {
        this.cartItems = cartItems;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderDescription='" + orderDescription + '\'' +
                ", cartItems=" + cartItems +
                ", customerEmail='" + userEmail + '\'' +
                ", customerName='" + userName + '\'' +
                '}';
    }
}
