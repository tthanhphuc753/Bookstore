//package com.example.Bookstore.Presentation.Controller.OrderController;
//
//import com.example.Bookstore.Domain.Model.User.User;
//import com.example.Bookstore.Domain.OrderService.OrderService;
//import com.example.Bookstore.Domain.Security.JWTAuth.JwtService;
//import com.example.Bookstore.Presentation.Controller.ShoppingCartController.ShoppingCartController;
//import com.example.Bookstore.Presentation.Controller.userController.UserController;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//import static org.mockito.ArgumentMatchers.*;
//
//@ExtendWith(MockitoExtension.class)
//class OrderControllerTest {
//
//
//    @Mock
//    private OrderService orderService;
//    @Mock
//    private UserController userController;
//    @Mock
//    private JwtService jwtService;
//    @Mock
//    private ShoppingCartController shoppingCartController;
//
//    @Test
//    void addOrder() throws IOException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//
//        // Tạo instance của class chứa hàm cần kiểm thử
//        OrderController orderController = new OrderController(orderService, userController, jwtService, shoppingCartController);
//
//        // Thiết lập mock behavior cho userController
//        when(userController.findByEmail(anyString())).thenReturn(Optional.of(new User())); // Điều chỉnh user theo nhu cầu
//
//        // Thiết lập mock behavior cho request và jwtService (nếu cần)
//
//        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie("JWT_TOKEN", "mockedToken")});
//
//
//        // Điều chỉnh cookies theo nhu cầu
//      //  when(orderController.getJwtFromCookie(eq(request))).thenReturn("mockedToken");
//
//        // Gọi hàm cần kiểm thử
//        orderController.addOrder(request, session, response);
//
//        // Kiểm tra xem các phương thức đã được gọi đúng cách hay không
//        verify(userController, times(1)).findByEmail(anyString());
//        verify(orderService, times(1)).addOrder(any(), any());
//        verify(shoppingCartController, times(1)).clearCart(eq(session));
//        verify(response, never()).sendRedirect(anyString()); // Response không được gọi khi có token
//
//        // Kiểm tra logic khi không có token
//        when(request.getCookies()).thenReturn(null); // Set cookie trả về null
//        when(orderController.getJwtFromCookie(request)).thenReturn(null);
//        orderController.addOrder(request, session, response);
//        verify(response, times(1)).sendRedirect("/auth/login"); // Response được gọi khi không có token
//    }
//}
