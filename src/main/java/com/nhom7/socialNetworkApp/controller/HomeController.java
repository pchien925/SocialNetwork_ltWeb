package com.nhom7.socialNetworkApp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableMethodSecurity
public class HomeController {
    @RequestMapping("/home")

//ROLE_ADMIN
    @PreAuthorize("hasAuthority('ADMIN')")
    public String home() {
        return "web/home";
    }
}
