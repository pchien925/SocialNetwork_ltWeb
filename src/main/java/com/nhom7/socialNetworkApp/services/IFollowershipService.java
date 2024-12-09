package com.nhom7.socialNetworkApp.services;

import java.util.List;
import java.util.Optional;

import com.nhom7.socialNetworkApp.entity.Followership;
import com.nhom7.socialNetworkApp.entity.User;

public interface IFollowershipService {

	Optional<Followership> findById(Long id);

	void addFollower(User userFollower, User userChecked);

	<S extends Followership> S save(S entity);

	Optional<Followership> findByUserFollowerAndUserChecked(User userFollower, User userChecked);

	void deleteById(Long id);

	List<Followership> findAllById(Iterable<Long> ids);

	Integer findFollowingQuantity(Long idUser);

	Integer findFollowersQuantity(Long idUser);
	
}
