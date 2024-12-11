package com.nhom7.socialNetworkApp.controller.api;


import com.nhom7.socialNetworkApp.entity.Post;
import com.nhom7.socialNetworkApp.services.IPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostProfileRestController {

    private final IPostService postService;
    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return postService.getPostsByUserId(userId);
    }
}
