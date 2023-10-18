package com.example.bookstore.repository;


import com.example.bookstore.model.Book;
import com.example.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByCategoryId(int categoryId);
    List<Book> findByName(String name);



}
