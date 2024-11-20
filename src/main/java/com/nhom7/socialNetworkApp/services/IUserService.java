package com.nhom7.socialNetworkApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.model.UserModel;

public interface IUserService {

	void deleteById(Long id);

	long count();

	boolean existsById(Long id);

	Optional<User> findById(Long id);

	List<User> findAll();

	List<User> findAll(Sort sort);

	<S extends User> S save(S entity);


	User login(String username, String password);

	Page<User> findByLastNameContaining(String lastName, Pageable pageable);

	Page<User> findByFirstNameContaining(String firstName, Pageable pageable);

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	String regenerateOtp(String email);

	String verifyAccount(String email, String otp);

	String register(UserModel UserModel);

	String ChangePassword(String email, String password, String otp);
	
}
