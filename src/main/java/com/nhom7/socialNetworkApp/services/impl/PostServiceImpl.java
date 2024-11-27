package com.nhom7.socialNetworkApp.services.impl;

import com.nhom7.socialNetworkApp.dto.request.PostCreationRequest;
import com.nhom7.socialNetworkApp.dto.response.PostResponse;
import com.nhom7.socialNetworkApp.entity.Post;
import com.nhom7.socialNetworkApp.mapper.PostMapper;
import com.nhom7.socialNetworkApp.repository.PostRepository;
import com.nhom7.socialNetworkApp.repository.UserRepository;
import com.nhom7.socialNetworkApp.services.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    @Override
    public PostResponse get(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return postMapper.toPostResponse(post);
    }

    @Override
    public PostResponse create(PostCreationRequest postCreationRequest) {
        Post post = postMapper.toPost(postCreationRequest);
        post.setUser(userRepository.findById(postCreationRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        post = postRepository.save(post);
        return postMapper.toPostResponse(post);
    }

    @Override
    public PostResponse update(Long postId, PostCreationRequest postCreationRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        post.setText(postCreationRequest.getText());
        post.setPhoto(postCreationRequest.getPhoto());
        post = postRepository.save(post);
        return postMapper.toPostResponse(post);
    }

    @Override
    public PostResponse delete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
        return postMapper.toPostResponse(post);
    }

    @Override
    public PostResponse getPostsByUser(Long userId) {
        Post post = postRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return postMapper.toPostResponse(post);
    }
}
