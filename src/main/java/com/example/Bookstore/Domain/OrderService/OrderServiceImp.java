package com.example.Bookstore.Domain.OrderService;

import com.example.Bookstore.Domain.Model.Cart.CartItem;
import com.example.Bookstore.Domain.Model.Order.Order;
import com.example.Bookstore.Persistence.DAO.CartRepository;
import com.example.Bookstore.Persistence.DAO.OrderdetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService{
    private final CartRepository cartRepository;
    private final OrderdetailRepository orderdetailRepository;
    @Override
    public Order addOrder(Long cartId) {
        CartItem cartItem = cartRepository.findById(cartId).get();

            Order newOrder = new Order();
            newOrder.setQuantity(cartItem.getQuantity());
            newOrder.setBookName(cartItem.getBook().getName());
            return orderdetailRepository.save(newOrder);

    }

    @Override
    public void deleteOrderById(long id) {

    }



    @Override
    public List<Order> getAllOrder() {
        return orderdetailRepository.findAll();
    }

    @Override
    public Optional<Order> findOrderById(Long id) {
        return Optional.empty();
    }
}
