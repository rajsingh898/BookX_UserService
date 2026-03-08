package com.book.UserService.service;

import com.book.UserService.DTO.*;
import com.book.UserService.Exceptions.NotFoundException;
import com.book.UserService.entity.User;
import com.book.UserService.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository ) {
        this.userRepository = userRepository;
     }

    public UserInfoDTO userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        User u1 = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new NotFoundException("User not found"));
        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setEmail(u1.getEmail());
        userInfo.setFullName(u1.getFullName());
        userInfo.setRole(u1.getRole());
        userInfo.setIsActive(u1.getIsActive());
        return userInfo;
    }

    public HashMap<String,String> exchangedUserName(UUID exchangedUserId) {
        User u = userRepository.findById(exchangedUserId).orElseThrow(() -> new NotFoundException("User not found"));
        HashMap<String,String> idAndEmail = new HashMap<>();
        idAndEmail.put(u.getEmail(), u.getFullName());
        return idAndEmail;
    }
}
