package com.example.bookstore.repository;


import com.example.bookstore.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    public User  getUserByEmailAndName(String email,String name);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

//    Optional<User> findByEmail(String email);
    @Query("select count (u) from User u join u.roles r where r.name='ROLE_USER'")
    long countUser();
    @Query("select  (u) from User u join u.roles r where r.name='ROLE_USER'")
    List<User> getListUser();

}
