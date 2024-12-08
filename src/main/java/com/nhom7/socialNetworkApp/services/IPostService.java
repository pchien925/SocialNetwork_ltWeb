package com.nhom7.socialNetworkApp.services;

import com.nhom7.socialNetworkApp.dto.request.PostCreationRequest;
import com.nhom7.socialNetworkApp.dto.response.PageResponse;
import com.nhom7.socialNetworkApp.dto.response.PostResponse;

public interface IPostService {
    PostResponse createPost(PostCreationRequest postCreationRequest);

    PostResponse get(Long postId);

    String delete(Long postId);

    PostResponse update(Long postId, PostCreationRequest postCreationRequest);

    PageResponse<PostResponse> getAll(int page, int size, String sortBy);

    PageResponse<PostResponse> getAllByUser(int page, int size, String sortBy);
}
