package com.example.SalesManagment.Controller.Aut;

import lombok.*;


public record AuthenticateRequest( String email,
                                   String password) {


}
