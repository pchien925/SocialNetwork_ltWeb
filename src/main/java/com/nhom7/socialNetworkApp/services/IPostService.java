package com.nhom7.socialNetworkApp.services;

import com.nhom7.socialNetworkApp.dto.request.PostCreationRequest;
import com.nhom7.socialNetworkApp.dto.response.PostResponse;

public interface IPostService {
    PostResponse get(Long postId);

    PostResponse create(PostCreationRequest postCreationRequest);

    PostResponse update(Long postId, PostCreationRequest postCreationRequest);

    PostResponse delete(Long postId);

    PostResponse getPostsByUser(Long userId);
}
