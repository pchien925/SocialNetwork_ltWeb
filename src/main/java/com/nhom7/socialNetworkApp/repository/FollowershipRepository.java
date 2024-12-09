package com.nhom7.socialNetworkApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhom7.socialNetworkApp.entity.Followership;
import com.nhom7.socialNetworkApp.entity.User;

public interface FollowershipRepository extends JpaRepository<Followership, Long>{
	
	Optional<Followership> findById(Long id);
	Optional<Followership> findByUserFollowerAndUserChecked(User userFollower, User userChecked);
	@Query(value = "SELECT COUNT(*) FROM followership f WHERE " +
            "f.user_checked_id = ?",
            nativeQuery = true)
    Integer findFollowersQuantity(Long idUser);

    @Query(value = "SELECT COUNT(*) FROM followership f WHERE " +
            "f.user_follower_id = ?",
            nativeQuery = true)
    Integer findFollowingQuantity(Long idUser);
}
