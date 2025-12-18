package com.book.UserService.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RegisterResponseDTO {
    @NotNull
    private UUID id;
    @Email
    @NotBlank
    private String email;
    @NotNull
    private String fullName;
}
