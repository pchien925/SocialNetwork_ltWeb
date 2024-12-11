package com.nhom7.socialNetworkApp.controller.api;


import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserByUsernameRestApi {


    private final IUserService userService;

    @GetMapping("/user/profile")
    public User getUserProfile() {
        return userService.getAuthenticatedUser();
    }
}
