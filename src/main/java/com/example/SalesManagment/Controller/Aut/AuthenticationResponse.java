package com.example.SalesManagment.Controller.Aut;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
public record AuthenticationResponse(String username, String name, String role, String token) {
}
