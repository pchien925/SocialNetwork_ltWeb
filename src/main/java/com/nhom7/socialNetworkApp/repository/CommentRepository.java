package com.nhom7.socialNetworkApp.repository;

import com.nhom7.socialNetworkApp.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByPost_IdAndReplies_IdNull(Long id, Pageable pageable);

    long countByPost_Id(Long id);
}
