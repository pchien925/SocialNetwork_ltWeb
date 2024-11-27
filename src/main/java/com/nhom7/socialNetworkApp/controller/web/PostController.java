package com.nhom7.socialNetworkApp.controller.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    @GetMapping("/post")
    public String postDetail(HttpSession httpSession){
        httpSession.setAttribute("userId", 1);

        return "web/createPost";
    }
}
