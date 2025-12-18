package com.book.UserService.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class LoginResponseDTO {
    @NotNull
    private String accessToken;
    @NotNull
    private String tokenType = "Bearer";
    @NotNull
    private double timeOut=1800.0;
}
