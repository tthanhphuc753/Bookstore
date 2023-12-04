package com.example.SalesManagment.Presentation.Controller.Aut;

import lombok.Builder;


@Builder
public record AuthenticationResponse(String username
        , String name, String role, String token) {
}
