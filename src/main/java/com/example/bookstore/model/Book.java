package com.example.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(
            name = "categoryId",
            referencedColumnName = "id"
    )
    private Category category;
    private Long price;
    private String photo;
    private Date created;
    private int availableQuantity;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", photo='" + photo + '\'' +
                ", created=" + created +
                ", availableQuantity=" + availableQuantity +
                '}';
    }
}
