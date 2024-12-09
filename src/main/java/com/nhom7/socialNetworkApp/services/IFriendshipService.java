package com.nhom7.socialNetworkApp.services;

import java.util.Optional;

import com.nhom7.socialNetworkApp.entity.Friendship;
import com.nhom7.socialNetworkApp.entity.User;

public interface IFriendshipService {

	<S extends Friendship> S save(S entity);

	void addFriend(User userTransmitter, User userReceiver);

	Optional<Friendship> findById(Long id);

	Optional<Friendship> findByUsers(User userTransmitter, User userReceiver);

	Integer findFriendsQuantity(Long idUser);

	void deleteById(Long id);

	
}
