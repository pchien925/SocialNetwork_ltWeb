package com.nhom7.socialNetworkApp.controller.api;

import com.nhom7.socialNetworkApp.dto.request.LikeRequest;
import com.nhom7.socialNetworkApp.dto.response.ApiResponse;
import com.nhom7.socialNetworkApp.dto.response.LikeResponse;
import com.nhom7.socialNetworkApp.dto.response.PageResponse;
import com.nhom7.socialNetworkApp.services.ILikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeRestController {
    private final ILikeService likeService;

    @GetMapping("/post/{postId}/count")
    public ApiResponse<Long> countByPostId(@PathVariable Long postId) {
        return ApiResponse.<Long>builder()
                .status(HttpStatus.OK.value())
                .data(likeService.countByPostId(postId))
                .build();
    }

    @PostMapping
    public ApiResponse<LikeResponse> create(@RequestBody LikeRequest request) {
        return ApiResponse.<LikeResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(likeService.create(request))
                .build();
    }

    @DeleteMapping("/post/{postId}")
    public ApiResponse<String> delete(@PathVariable Long postId) {
        return ApiResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .data(likeService.delete(postId))
                .build();
    }

    @GetMapping("/post/{postId}")
    public ApiResponse<PageResponse<LikeResponse>> getAllByPost(@PathVariable("postId") long postId,
                                                               @RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "5") int size,
                                                               @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy) {
        return ApiResponse.<PageResponse<LikeResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(likeService.getAllByPost(postId, page, size, sortBy))
                .build();
    }

    @GetMapping("/post/{postId}/is-liked")
    public ApiResponse<Boolean> isLiked(@PathVariable Long postId) {
        return ApiResponse.<Boolean>builder()
                .status(HttpStatus.OK.value())
                .data(likeService.isLiked(postId))
                .build();
    }

    @PostMapping("/post/{postId}")
    public ApiResponse<LikeResponse> createLike(@PathVariable Long postId) {
        return ApiResponse.<LikeResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(likeService.createLike(postId))
                .build();
    }

}
