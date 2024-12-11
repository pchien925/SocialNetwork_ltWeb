package com.nhom7.socialNetworkApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nhom7.socialNetworkApp.entity.Friendship;
import com.nhom7.socialNetworkApp.entity.User;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long>{
	@Query("SELECT f FROM Friendship f WHERE f.userTransmitter = :userTransmitter AND f.userReceiver = :userReceiver")
    Optional<Friendship> findByUsers(@Param("userTransmitter") User userTransmitter, @Param("userReceiver") User userReceiver);
	
	Optional<Friendship> findById(Long id);
	@Query(value = "SELECT COUNT(*) FROM friendship f WHERE " +
            "(f.user_receiver_id = ?1 OR f.user_transmitter_id = ?1) && f.is_accept = 1",
            nativeQuery = true)
    Integer findFriendsQuantity(Long idUser);
}
