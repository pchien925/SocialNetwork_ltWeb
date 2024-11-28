package com.nhom7.socialNetworkApp.controller.api;

import com.nhom7.socialNetworkApp.dto.request.PostCreationRequest;
import com.nhom7.socialNetworkApp.dto.response.ApiResponse;
import com.nhom7.socialNetworkApp.dto.response.PostResponse;
import com.nhom7.socialNetworkApp.services.IPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@Slf4j
public class PostRestController {
    private final IPostService postService;

    @PostMapping("/create")
    public ApiResponse<PostResponse> create(@RequestBody PostCreationRequest request){
        return ApiResponse.<PostResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(postService.createPost(request))
                .build();
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostResponse> getPostsByUserId(@PathVariable Long postId){
        return ApiResponse.<PostResponse>builder()
                .status(HttpStatus.OK.value())
                .data(postService.get(postId))
                .build();
    }
}
