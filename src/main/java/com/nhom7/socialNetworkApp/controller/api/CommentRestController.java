package com.nhom7.socialNetworkApp.controller.api;

import com.nhom7.socialNetworkApp.dto.response.ApiResponse;
import com.nhom7.socialNetworkApp.dto.response.CommentResponse;
import com.nhom7.socialNetworkApp.dto.response.PageResponse;
import com.nhom7.socialNetworkApp.services.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentRestController {
    private final ICommentService commentService;

    @GetMapping("/post/{postId}")
    public ApiResponse<PageResponse<CommentResponse>> getAllByPost(@PathVariable("postId") long postId,
                                                                   @RequestParam(value = "page", defaultValue = "1") int page,
                                                                   @RequestParam(value = "size", defaultValue = "5") int size,
                                                                   @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy) {
        return ApiResponse.<PageResponse<CommentResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(commentService.getAllByPost(postId, page, size, sortBy))
                .build();
    }

    @GetMapping("/post/{postId}/count")
    public ApiResponse<Long> countByPostId(@PathVariable Long postId) {
        return ApiResponse.<Long>builder()
                .status(HttpStatus.OK.value())
                .data(commentService.countByPost(postId))
                .build();
    }
}
