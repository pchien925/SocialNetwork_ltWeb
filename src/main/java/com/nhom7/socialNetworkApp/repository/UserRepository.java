package com.nhom7.socialNetworkApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom7.socialNetworkApp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByid(Long id);
	
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Page<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName, Pageable pageable);
	@Query(value = "SELECT u.* FROM user u " +
            "INNER JOIN friendship f ON u.id = f.user_transmitter_id " +
            "OR u.id = f.user_receiver_id " +
            "WHERE (f.user_transmitter_id = ?1 OR f.user_receiver_id = ?1) " +
            "AND u.id != ?1 AND f.is_accept = 1",
            nativeQuery = true)
    List<User> findFriendsByUser(Long idUser);
	@Query(value = "SELECT u.* FROM user u " +
            "INNER JOIN followership f ON u.id= f.user_follower_id " +
            "WHERE f.user_checked_id = ?",
            nativeQuery = true)
    List<User> findFollowersByUser(Long idUser);

    @Query(value = "SELECT u.* FROM user u " +
            "INNER JOIN followership f ON u.id = f.user_checked_id " +
            "WHERE f.user_follower_id = ?",
            nativeQuery = true)
    List<User> findFollowingByUser(Long idUser);
}
