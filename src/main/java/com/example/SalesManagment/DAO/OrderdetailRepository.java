package com.example.SalesManagment.DAO;

import com.example.SalesManagment.Model.Product.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderdetailRepository extends JpaRepository<OrderDetail,Long> {

}
