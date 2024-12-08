package com.nhom7.socialNetworkApp.services.impl;

import com.nhom7.socialNetworkApp.dto.request.LikeRequest;
import com.nhom7.socialNetworkApp.dto.response.LikeResponse;
import com.nhom7.socialNetworkApp.dto.response.PageResponse;
import com.nhom7.socialNetworkApp.entity.Like;
import com.nhom7.socialNetworkApp.mapper.LikeMapper;
import com.nhom7.socialNetworkApp.repository.LikeRepository;
import com.nhom7.socialNetworkApp.repository.PostRepository;
import com.nhom7.socialNetworkApp.repository.UserRepository;
import com.nhom7.socialNetworkApp.services.ILikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements ILikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeMapper likeMapper;

    @Override
    public LikeResponse create(LikeRequest request){
        Like like = Like.builder()
                .user(userRepository.findById(request.getUserId()).orElseThrow(
                        () -> new RuntimeException("User not found")
                ))
                .post(postRepository.findById(request.getPostId()).orElseThrow(
                        () -> new RuntimeException("Post not found")
                ))
                .build();
        likeRepository.save(like);
        return likeMapper.toLikeResponse(like);
    }

    @Override
    public LikeResponse createLike(Long postId) {
        Like like = Like.builder()
                .user(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(
                        () -> new RuntimeException("User not found")
                ))
                .post(postRepository.findById(postId).orElseThrow(
                        () -> new RuntimeException("Post not found")
                ))
                .build();
        likeRepository.save(like);
        return likeMapper.toLikeResponse(like);
    }

    @Override
    public boolean isLiked(Long postId) {
        return likeRepository.existsByPost_IdAndUser_Username(postId, SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public String delete(Long postId) {
        Like like = likeRepository.findByPost_IdAndUser_Username(postId, SecurityContextHolder.getContext().getAuthentication().getName());
        likeRepository.delete(like);
        return "Like deleted successfully";
    }

    @Override
    public PageResponse<LikeResponse> getAllByPost(long postId, int page, int size, String sortBy)  {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));

        Page<Like> likes = likeRepository.findByPost_Id(postId, pageable);
        List<LikeResponse> likeContents = likes.getContent().stream().map(likeMapper::toLikeResponse).toList();
        return PageResponse.<LikeResponse>builder()
                .content(likeContents)
                .currentPage(page)
                .pageSize(size)
                .totalPages(likes.getTotalPages())
                .totalElements(likes.getTotalElements())
                .build();
    }

    @Override
    public long countByPostId(Long postId) {
        return likeRepository.countByPost_Id(postId);
    }
}
