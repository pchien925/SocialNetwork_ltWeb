package com.nhom7.socialNetworkApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom7.socialNetworkApp.entity.Followership;
import com.nhom7.socialNetworkApp.entity.User;

public interface FollowershipRepository extends JpaRepository<Followership, Long>{
	
	Optional<Followership> findById(Long id);
	Optional<Followership> findByUserFollowerAndUserChecked(User userFollower, User userChecked);
}
