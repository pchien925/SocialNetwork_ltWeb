package com.nhom7.socialNetworkApp.services;

import com.nhom7.socialNetworkApp.dto.request.LikeRequest;
import com.nhom7.socialNetworkApp.dto.response.LikeResponse;
import com.nhom7.socialNetworkApp.dto.response.PageResponse;

import java.util.List;

public interface ILikeService {
    LikeResponse create(LikeRequest request);

    String delete(LikeRequest request);

    PageResponse<LikeResponse> getAllByUser(long postId, int page, int size, String sortBy);

    long countByPostId(Long postId);
}
