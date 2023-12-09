package com.example.Bookstore.Persistence.DAO;

import com.example.Bookstore.Domain.Model.Book.OrderDetail;
import com.example.Bookstore.Domain.Model.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderdetailRepository extends JpaRepository<Order,Long> {


}
