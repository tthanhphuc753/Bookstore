package com.example.Bookstore.Domain.AuthService;


public record RegistrationRequest(String firstName,
                                  String lastName,
                                  String email,
                                  String password) {

}
