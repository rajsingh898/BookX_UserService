package com.book.UserService.controller.user;

import com.book.UserService.DTO.UserInfoDTO;
import com.book.UserService.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserController(UserService userService){
        this.userService =   userService;
    }
    @GetMapping("/me")
    public UserInfoDTO userInfo() {
        return userService.userInfo();
    }
}
