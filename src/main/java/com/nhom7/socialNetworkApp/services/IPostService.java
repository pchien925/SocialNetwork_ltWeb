package com.nhom7.socialNetworkApp.services;

import com.nhom7.socialNetworkApp.dto.request.PostCreationRequest;
import com.nhom7.socialNetworkApp.dto.response.PostResponse;

public interface IPostService {
    PostResponse createPost(PostCreationRequest postCreationRequest);

    PostResponse get(Long postId);
}
