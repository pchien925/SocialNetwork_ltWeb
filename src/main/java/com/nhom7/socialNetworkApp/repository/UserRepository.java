package com.nhom7.socialNetworkApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom7.socialNetworkApp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Page<User> findByFirstNameContaining(String firstName, Pageable pageable);

	Page<User> findByLastNameContaining(String lastName, Pageable pageable);

	Optional<User> findByid(Long id);
	List<User> findByEmailContaining(String email);
	Page<User> findByEmailContaining(String Email,Pageable page);
	List<User> findByUsernameContaining(String username);
	
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
}
