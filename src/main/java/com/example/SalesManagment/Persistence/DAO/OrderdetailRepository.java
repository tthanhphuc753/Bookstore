package com.example.SalesManagment.Persistence.DAO;

import com.example.SalesManagment.Domain.Model.Book.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderdetailRepository extends JpaRepository<OrderDetail,Long> {

}
