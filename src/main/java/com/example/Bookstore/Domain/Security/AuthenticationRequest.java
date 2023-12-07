package com.example.Bookstore.Domain.Security;

public record AuthenticationRequest(String email,
                                    String password) {


}
