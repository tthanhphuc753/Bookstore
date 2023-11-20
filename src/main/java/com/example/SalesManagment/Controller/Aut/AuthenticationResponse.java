package com.example.SalesManagment.Controller.Aut;

import lombok.Builder;


@Builder
public record AuthenticationResponse(String username
        , String name, String role, String token) {
}
