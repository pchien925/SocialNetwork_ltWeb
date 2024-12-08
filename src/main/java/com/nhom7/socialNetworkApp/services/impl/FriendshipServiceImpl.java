package com.nhom7.socialNetworkApp.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom7.socialNetworkApp.entity.Friendship;
import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.repository.FriendshipRepository;
import com.nhom7.socialNetworkApp.services.IFriendshipService;

@Service
public class FriendshipServiceImpl implements IFriendshipService{
	@Autowired
	FriendshipRepository friendshipRepository;

	@Override
	public <S extends Friendship> S save(S entity) {
		return friendshipRepository.save(entity);
	}
	@Override
	public void addFriend(User userTransmitter, User userReceiver)
	{
		Friendship friendship=new Friendship();
		friendship.setUserTransmitter(userTransmitter);
		friendship.setUserReceiver(userReceiver);
		friendship.setIsAccept(false);
		save(friendship);
	}
	
	@Override
	public Optional<Friendship> findById(Long id) {
		return friendshipRepository.findById(id);
	}
	@Override
	public Optional<Friendship> findByUsers(User userTransmitter, User userReceiver) {
		return friendshipRepository.findByUsers(userTransmitter, userReceiver);
	}
	
	
	
}	
