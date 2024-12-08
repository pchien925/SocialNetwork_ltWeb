package com.nhom7.socialNetworkApp.repository;

import com.nhom7.socialNetworkApp.entity.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    long countByPost_Id(Long postId);

    Page<Like> findByPost_Id(Long id, Pageable pageable);

    boolean existsByPost_IdAndUser_Username(Long postId, String name);

    Like findByPost_IdAndUser_Username(Long id, String username);
}
