package com.example.Bookstore.Presentation.Controller.OrderController;

import com.example.Bookstore.Domain.Model.Order.Order;
import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.OrderService.OrderService;
import com.example.Bookstore.Domain.Security.JWTAuth.JwtService;
import com.example.Bookstore.Presentation.Controller.ShoppingCartController.ShoppingCartController;
import com.example.Bookstore.Presentation.Controller.userController.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserController userController;
    private final JwtService jwtService;
    private final ShoppingCartController shoppingCartController;

    public List<Order> getAllOrder() {
        return orderService.getAllOrder();
    }


    @Transactional
    public void addOrder(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        String token = getJwtFromCookie(request);
        Long userId = null;
        if (token != null) {
            Optional<User> optionalUser = userController.findByEmail(jwtService.extractUsername(token));
            User user = optionalUser.get();
            userId = user.getUserID();
        }else
        {
            response.sendRedirect("/auth/login");
            return;
        }
        orderService.addOrder(userId, session);
        shoppingCartController.clearCart(session);
    }

    public void deleteOrder(Long orderId) {
        orderService.deleteOrderById(orderId);
    }

    String getJwtFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JWT_TOKEN".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
