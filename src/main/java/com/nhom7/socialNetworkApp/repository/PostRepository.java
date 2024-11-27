package com.nhom7.socialNetworkApp.repository;

import com.nhom7.socialNetworkApp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUserId(Long userId);
}
