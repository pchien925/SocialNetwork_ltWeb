package com.nhom7.socialNetworkApp.services.impl;

import com.nhom7.socialNetworkApp.dto.request.CommentCreationRequest;
import com.nhom7.socialNetworkApp.dto.response.CommentResponse;
import com.nhom7.socialNetworkApp.dto.response.PageResponse;
import com.nhom7.socialNetworkApp.entity.Comment;
import com.nhom7.socialNetworkApp.repository.CommentRepository;
import com.nhom7.socialNetworkApp.repository.PostRepository;
import com.nhom7.socialNetworkApp.repository.UserRepository;
import com.nhom7.socialNetworkApp.services.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public CommentResponse create(CommentCreationRequest request){
        Comment comment = Comment.builder()
                .text(request.getText())
                .user(userRepository.findById(request.getUserId()).orElseThrow(
                        () -> new RuntimeException("User not found")
                ))
                .post(postRepository.findById(request.getPostId()).orElseThrow(
                        () -> new RuntimeException("Post not found")
                ))
                .build();
        commentRepository.save(comment);
        return CommentResponse.builder()
                .id(comment.getId())
                .postId(comment.getPost().getId())
                .userId(comment.getUser().getId())
                .username(comment.getUser().getUsername())
                .avatar(comment.getUser().getAvatar())
                .text(comment.getText())
                .build();
    }

    @Override
    public PageResponse<CommentResponse> getAllByPost(long postId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));

        Page<Comment> comments = commentRepository.findByPost_IdAndReplies_IdNull(postId, pageable);
        return PageResponse.<CommentResponse>builder()
                .content(comments.getContent().stream().map(comment -> CommentResponse.builder()
                        .id(comment.getId())
                        .postId(comment.getPost().getId())
                        .userId(comment.getUser().getId())
                        .username(comment.getUser().getUsername())
                        .avatar(comment.getUser().getAvatar())
                        .text(comment.getText())
                        .build()).toList())
                .currentPage(page)
                .pageSize(size)
                .totalPages(comments.getTotalPages())
                .totalElements(comments.getTotalElements())
                .build();
    }

    @Override
    public Long countByPost(long postId) {
        return commentRepository.countByPost_Id(postId);
    }
}
