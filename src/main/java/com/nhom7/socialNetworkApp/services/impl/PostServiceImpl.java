package com.nhom7.socialNetworkApp.services.impl;

import com.nhom7.socialNetworkApp.dto.request.PostCreationRequest;
import com.nhom7.socialNetworkApp.dto.response.PageResponse;
import com.nhom7.socialNetworkApp.dto.response.PostResponse;
import com.nhom7.socialNetworkApp.entity.Post;
import com.nhom7.socialNetworkApp.mapper.PostMapper;
import com.nhom7.socialNetworkApp.repository.LikeRepository;
import com.nhom7.socialNetworkApp.repository.PostRepository;
import com.nhom7.socialNetworkApp.repository.UserRepository;
import com.nhom7.socialNetworkApp.services.IPostService;
import com.nhom7.socialNetworkApp.util.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements IPostService {
    private final DateTimeFormatter dateTimeFormatter;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    private final UserRepository userRepository;

    @Override
    public PostResponse createPost(PostCreationRequest postCreationRequest) {
        Post post = postMapper.toPost(postCreationRequest);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        post.setUser(userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found")
        ));
        postRepository.save(post);
        return postMapper.toPostResponse(post);
    }

    @Override
    public PostResponse get(Long postId) {
        PostResponse response = postMapper.toPostResponse(postRepository.findById(postId).orElseThrow(
                () -> new RuntimeException("Post not found")
        ));
        response.setCreated(dateTimeFormatter.format(response.getCreatedAt()));
        return response;
    }

    @Override
    public String delete(Long postId) {
        postRepository.deleteById(postId);
        return "Post deleted successfully";
    }

    @Override
    public PostResponse update(Long postId, PostCreationRequest postCreationRequest) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RuntimeException("Post not found")
        );
        postMapper.update(post, postCreationRequest);
        postRepository.save(post);
        return postMapper.toPostResponse(post);
    }

    @Override
    public PageResponse<PostResponse> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy).descending());

        Page<Post> posts = postRepository.findAll(pageable);

        List<PostResponse> postResponses = posts.map(postMapper::toPostResponse).getContent();

        for (PostResponse postResponse : postResponses) {
            postResponse.setCreated(dateTimeFormatter.format(postResponse.getCreatedAt()));
        }

        return PageResponse.<PostResponse>builder()
                .content(postResponses)
                .currentPage(page)
                .pageSize(size)
                .totalPages(posts.getTotalPages())
                .totalElements(posts.getTotalElements())
                .build();
    }

    @Override
    public PageResponse<PostResponse> getAllByUser(int page, int size, String sortBy) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy).descending());

        Page<Post> posts = postRepository.findByUser_Username(username, pageable);

        List<PostResponse> postResponses = posts.map(postMapper::toPostResponse).getContent();

        for (PostResponse postResponse : postResponses) {
            postResponse.setCreated(dateTimeFormatter.format(postResponse.getCreatedAt()));
        }
        return PageResponse.<PostResponse>builder()
                .content(postResponses)
                .currentPage(page)
                .pageSize(size)
                .totalPages(posts.getTotalPages())
                .totalElements(posts.getTotalElements())
                .build();
    }


}
