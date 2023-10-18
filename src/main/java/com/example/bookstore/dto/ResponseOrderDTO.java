package com.example.bookstore.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class ResponseOrderDTO {

    private float amount;

    private String date;
    private String OrderDescription;
    private int orderId;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderDescription() {
        return OrderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        OrderDescription = orderDescription;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
