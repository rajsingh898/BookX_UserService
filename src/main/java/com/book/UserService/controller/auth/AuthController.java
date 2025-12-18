package com.book.UserService.controller.auth;

import com.book.UserService.DTO.LoginRequestDTO;
import com.book.UserService.DTO.LoginResponseDTO;
import com.book.UserService.DTO.RegisterRequestDTO;
import com.book.UserService.DTO.RegisterResponseDTO;
import com.book.UserService.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public RegisterResponseDTO register(@Valid @RequestBody RegisterRequestDTO rrd) {
        return authService.register(rrd);
    }
    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO rrd){
    return authService.login(rrd);
    }

    @PostMapping("/logout")
    public void logout() {

    }
}
