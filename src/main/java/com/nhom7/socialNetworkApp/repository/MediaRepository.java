package com.nhom7.socialNetworkApp.repository;

import com.nhom7.socialNetworkApp.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    boolean existsByMediaKey(String mediaKey);
}