package com.example.Bookstore.Domain.Security;


public record RegistrationRequest(String firstName,
                                  String lastName,
                                  String email,
                                  String password) {

}
