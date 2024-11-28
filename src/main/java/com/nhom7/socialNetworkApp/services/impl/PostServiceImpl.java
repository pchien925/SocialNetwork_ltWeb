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
    public PostResponse createPost(PostCreationRequest postCreationRequest) {
        Post post = postMapper.toPost(postCreationRequest);
        post.setUser(userRepository.findById(1L).orElseThrow(
                () -> new RuntimeException("User not found")
        ));
        postRepository.save(post);
        return postMapper.toPostResponse(post);
    }

    @Override
    public PostResponse get(Long postId) {
        return postMapper.toPostResponse(postRepository.findById(postId).orElseThrow(
                () -> new RuntimeException("Post not found")
        ));
    }
}
