package com.nhom7.socialNetworkApp.repository;

import com.nhom7.socialNetworkApp.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    long countByPost_Id(Long id);

    Page<Comment> findByParent_Id(Long id, Pageable pageable);

    Page<Comment> findByPost_IdAndParent_IdNull(Long id, Pageable pageable);

    Optional<List<Comment>> findCommentByParent_Id(Long id);
}
