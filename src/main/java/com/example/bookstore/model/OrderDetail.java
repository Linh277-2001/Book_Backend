package com.example.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@Entity
@ToString
@Data
@AllArgsConstructor
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int bookId;
    private String bookName;

    private int quantity;
    private float amount;

    public OrderDetail() {
    }

    public OrderDetail(int bookId, int quantity) {  //Có thể thêm các trường khác với giá trị mặc định
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public OrderDetail(int bookId, String bookName, int quantity, float amount) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.quantity = quantity;
        this.amount = amount;
    }



    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}
