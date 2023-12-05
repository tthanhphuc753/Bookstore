package com.example.Bookstore.Persistence.DAO;

import com.example.Bookstore.Domain.Model.Book.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderdetailRepository extends JpaRepository<OrderDetail,Long> {

}
