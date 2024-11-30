package com.nhom7.socialNetworkApp.controller.api;

import com.nhom7.socialNetworkApp.dto.request.PostCreationRequest;
import com.nhom7.socialNetworkApp.dto.response.ApiResponse;
import com.nhom7.socialNetworkApp.dto.response.PageResponse;
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
    public ApiResponse<PostResponse> getPosts(@PathVariable Long postId){
        return ApiResponse.<PostResponse>builder()
                .status(HttpStatus.OK.value())
                .data(postService.get(postId))
                .build();
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<String> deletePost(@PathVariable Long postId){
        return ApiResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .data(postService.delete(postId))
                .build();
    }

    @PutMapping("/{postId}")
    public ApiResponse<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostCreationRequest request){
        return ApiResponse.<PostResponse>builder()
                .status(HttpStatus.OK.value())
                .data(postService.update(postId, request))
                .build();
    }

    @GetMapping("/all")
    public ApiResponse<PageResponse<PostResponse>> getAllPosts(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "createdAt") String sortBy
    ){
        return ApiResponse.<PageResponse<PostResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(postService.getAll(page, size, sortBy))
                .build();
    }

    @GetMapping("/user/all")
    public ApiResponse<PageResponse<PostResponse>> getAllPostsByUser(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "createdAt") String sortBy
    ){
        return ApiResponse.<PageResponse<PostResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(postService.getAllByUser(page, size, sortBy))
                .build();
    }
}
