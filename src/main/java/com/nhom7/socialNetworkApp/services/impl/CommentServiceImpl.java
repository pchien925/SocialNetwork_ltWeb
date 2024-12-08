package com.nhom7.socialNetworkApp.services.impl;

import com.nhom7.socialNetworkApp.dto.request.CommentCreationRequest;
import com.nhom7.socialNetworkApp.dto.request.ReplyCommentRequest;
import com.nhom7.socialNetworkApp.dto.response.CommentResponse;
import com.nhom7.socialNetworkApp.dto.response.PageResponse;
import com.nhom7.socialNetworkApp.entity.Comment;
import com.nhom7.socialNetworkApp.mapper.CommentMapper;
import com.nhom7.socialNetworkApp.repository.CommentRepository;
import com.nhom7.socialNetworkApp.repository.PostRepository;
import com.nhom7.socialNetworkApp.repository.UserRepository;
import com.nhom7.socialNetworkApp.services.ICommentService;
import com.nhom7.socialNetworkApp.util.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
    private final DateTimeFormatter dateTimeFormatter;

    @Override
    public CommentResponse create(CommentCreationRequest request){
        Comment comment = Comment.builder()
                .text(request.getText())
                .user(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(
                        () -> new RuntimeException("User not found")
                ))
                .post(postRepository.findById(request.getPostId()).orElseThrow(
                        () -> new RuntimeException("Post not found")
                ))
                .build();
        commentRepository.save(comment);
        return commentMapper.toCommentResponse(comment);
    }

    @Override
    public CommentResponse createReply(ReplyCommentRequest request) {
        Comment parentComment = commentRepository.findById(request.getCommentId()).orElseThrow(
                () -> new RuntimeException("Comment not found")
        );
        Comment reply = Comment.builder()
                .text(request.getText())
                .user(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(
                        () -> new RuntimeException("User not found")
                ))
                .post(parentComment.getPost())
                .parent(parentComment)
                .build();
        commentRepository.save(reply);
        return commentMapper.toCommentResponse(reply);
    }

    @Override
    public PageResponse<CommentResponse> getAllByPost(long postId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));

        Page<Comment> comments = commentRepository.findByPost_IdAndParent_IdNull(postId, pageable);

        List<CommentResponse> commentResponses = comments.getContent().stream().map(commentMapper::toCommentResponse).toList();

        for (CommentResponse commentResponse : commentResponses) {
            commentResponse.setCreated(dateTimeFormatter.format(commentResponse.getCreatedAt()));
        }

        return PageResponse.<CommentResponse>builder()
                .content(commentResponses)
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

    @Override
    public PageResponse<CommentResponse> getReplies(long commentId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));

        Page<Comment> comments = commentRepository.findByParent_Id(commentId, pageable);

        List<CommentResponse> commentResponses = comments.getContent().stream().map(commentMapper::toCommentResponse).toList();

        for (CommentResponse commentResponse : commentResponses) {
            commentResponse.setCreated(dateTimeFormatter.format(commentResponse.getCreatedAt()));
        }

        return PageResponse.<CommentResponse>builder()
                .content(commentResponses)
                .currentPage(page)
                .pageSize(size)
                .totalPages(comments.getTotalPages())
                .totalElements(comments.getTotalElements())
                .build();
    }

    @Override
    public String delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new RuntimeException("Comment not found")
        );
        if (!Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), comment.getUser().getUsername()))
        {
            return "You are not authorized to delete this comment";
        }

        commentRepository.deleteAll(commentRepository.findCommentByParent_Id(commentId).orElse(List.of()));
        commentRepository.delete(comment);

        return "Comment deleted successfully";
    }
}
