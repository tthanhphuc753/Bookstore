package com.example.Bookstore.Persistence.DAO;

import com.example.Bookstore.Domain.Model.Cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findById(Long id);


}
