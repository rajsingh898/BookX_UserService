package com.book.UserService.service;

import com.book.UserService.DTO.*;
import com.book.UserService.Exceptions.BadRequestException;
import com.book.UserService.Exceptions.UnauthorizedException;
import com.book.UserService.entity.User;
import com.book.UserService.repository.UserRepository;
import com.book.UserService.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponseDTO register(RegisterRequestDTO rrd) {
        if (userRepository.existsByEmail(rrd.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        User user = new User();
        user.setEmail(rrd.getEmail());
        user.setPassword(passwordEncoder.encode(rrd.getPassword()));
        user.setFullName(rrd.getFullName());

        User savedUser = userRepository.save(user);

        //User savedUser = userRepository.findByEmail(user.getEmail());
        RegisterResponseDTO response = new RegisterResponseDTO();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setFullName(savedUser.getFullName());

        return response;
    }

    public LoginResponseDTO login(@Valid LoginRequestDTO rrd) {

        User user = userRepository.findByEmail(rrd.getEmail()).orElseThrow(() -> new UnauthorizedException("Invalid email or password"));
        if (!passwordEncoder.matches(rrd.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user);
        LoginResponseDTO response = new LoginResponseDTO();

        response.setAccessToken(token);

        return response;
    }

}
