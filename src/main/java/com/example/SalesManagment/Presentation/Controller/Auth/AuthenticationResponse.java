package com.example.SalesManagment.Presentation.Controller.Auth;

import lombok.Builder;


@Builder
public record AuthenticationResponse(String username
                                    , String name
                                    , String role
                                    , String token) {
}
