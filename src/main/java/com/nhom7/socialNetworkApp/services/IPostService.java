package com.nhom7.socialNetworkApp.services;

import com.nhom7.socialNetworkApp.dto.request.PostCreationRequest;
import com.nhom7.socialNetworkApp.dto.response.PageResponse;
import com.nhom7.socialNetworkApp.dto.response.PostResponse;
import com.nhom7.socialNetworkApp.entity.Post;

import java.util.List;

public interface IPostService {
    PostResponse createPost(PostCreationRequest postCreationRequest);

    PostResponse get(Long postId);

    String delete(Long postId);

    public long countPosts();
//    public List<Post> getPostsByUserId(Long userId);

    PostResponse update(Long postId, PostCreationRequest postCreationRequest);

    PageResponse<PostResponse> getAll(int page, int size, String sortBy);

    List<PostResponse> getPostsByUserId(Long userId);

    PageResponse<PostResponse> getAllByUser(int page, int size, String sortBy);
}
