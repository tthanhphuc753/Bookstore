package com.example.Bookstore.Presentation.Controller.Auth;


public record RegistrationRequest(String firstName,
                                  String lastName,
                                  String email,
                                  String password) {

}
