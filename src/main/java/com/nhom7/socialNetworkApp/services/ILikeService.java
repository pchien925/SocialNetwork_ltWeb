package com.nhom7.socialNetworkApp.services;

import com.nhom7.socialNetworkApp.dto.request.LikeRequest;
import com.nhom7.socialNetworkApp.dto.response.LikeResponse;
import com.nhom7.socialNetworkApp.dto.response.PageResponse;

import java.util.List;

public interface ILikeService {
    LikeResponse create(LikeRequest request);

    LikeResponse createLike(Long postId);

    boolean isLiked(Long postId);

    String delete(Long postId);

    PageResponse<LikeResponse> getAllByPost(long postId, int page, int size, String sortBy);

    long countByPostId(Long postId);
}
