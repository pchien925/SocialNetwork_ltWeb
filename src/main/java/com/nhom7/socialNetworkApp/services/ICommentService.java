package com.nhom7.socialNetworkApp.services;

import com.nhom7.socialNetworkApp.dto.request.CommentCreationRequest;
import com.nhom7.socialNetworkApp.dto.response.CommentResponse;
import com.nhom7.socialNetworkApp.dto.response.PageResponse;

public interface ICommentService {
    CommentResponse create(CommentCreationRequest request);

    PageResponse<CommentResponse> getAllByPost(long postId, int page, int size, String sortBy);

    Long countByPost(long postId);
}
