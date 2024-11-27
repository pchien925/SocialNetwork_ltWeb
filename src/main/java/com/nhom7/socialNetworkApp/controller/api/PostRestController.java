package com.nhom7.socialNetworkApp.controller.api;

import com.nhom7.socialNetworkApp.dto.request.PostCreationRequest;
import com.nhom7.socialNetworkApp.dto.request.RegisterDto;
import com.nhom7.socialNetworkApp.dto.response.ApiResponse;
import com.nhom7.socialNetworkApp.dto.response.PostResponse;
import com.nhom7.socialNetworkApp.model.UserModel;
import com.nhom7.socialNetworkApp.repository.UserRepository;
import com.nhom7.socialNetworkApp.services.IPostService;
import com.nhom7.socialNetworkApp.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostRestController {
    private final IPostService postService;
    private final IUserService userService;

    @GetMapping("/{postId}")
    ApiResponse<PostResponse> getPost(@PathVariable Long postId) {
        return ApiResponse.<PostResponse>builder()
                .status(HttpStatus.OK.value())
                .data(postService.get(postId))
                .build();
    }

    @PostMapping("/create")
    ApiResponse<PostResponse> createPost(@RequestBody PostCreationRequest postCreationRequest) {
        return ApiResponse.<PostResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(postService.create(postCreationRequest))
                .build();
    }

    @PutMapping("/{postId}")
    ApiResponse<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostCreationRequest postCreationRequest) {
        return ApiResponse.<PostResponse>builder()
                .status(HttpStatus.OK.value())
                .data(postService.update(postId, postCreationRequest))
                .build();
    }

    @DeleteMapping("/{postId}")
    ApiResponse<PostResponse> deletePost(@PathVariable Long postId) {
        return ApiResponse.<PostResponse>builder()
                .status(HttpStatus.OK.value())
                .data(postService.delete(postId))
                .build();
    }

    @GetMapping("/{userId}/posts")
    ApiResponse<PostResponse> getPostsByUser(@PathVariable Long userId) {
        return ApiResponse.<PostResponse>builder()
                .status(HttpStatus.OK.value())
                .data(postService.getPostsByUser(userId))
                .build();
    }
    @PostMapping("/test")
    ApiResponse<String> test(@RequestBody UserModel model) {
        return ApiResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .data(userService.register(model))
                .build();
    }
}
