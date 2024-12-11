package com.nhom7.socialNetworkApp.repository;

import com.nhom7.socialNetworkApp.entity.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUser_Username(String username, Pageable pageable);
    List<Post> findByUserId(Long userId);

}
