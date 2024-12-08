package com.nhom7.socialNetworkApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {
    @GetMapping("/create")
    public String createPost() {
        return "createPost";
    }

    @GetMapping("/my-posts")
    public String getMyPosts() {
        return "post-details";
    }

    @GetMapping("/newsfeed")
    public String getNewsfeed() {
        return "web/newsfeed";
    }

    @GetMapping("/test")
    public String test() {
        return "web/testPaging";
    }

    @GetMapping("/add")
    public String addPost() {
        return "web/add-post";
    }
}
