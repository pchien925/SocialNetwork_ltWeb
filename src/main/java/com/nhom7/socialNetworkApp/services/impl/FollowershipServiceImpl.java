package com.nhom7.socialNetworkApp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom7.socialNetworkApp.entity.Followership;
import com.nhom7.socialNetworkApp.entity.Friendship;
import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.repository.FollowershipRepository;
import com.nhom7.socialNetworkApp.services.IFollowershipService;

@Service
public class FollowershipServiceImpl implements IFollowershipService{
	@Autowired
	FollowershipRepository followershipRepository;

	public Optional<Followership> findById(Long id) {
		return followershipRepository.findById(id);
	}
	
	@Override
	public <S extends Followership> S save(S entity) {
		return followershipRepository.save(entity);
	}

	@Override
	public void addFollower(User userFollower, User userChecked)
	{
		Followership followership=new Followership();
		followership.setUserFollower(userFollower);
		followership.setUserChecked(userChecked);
		save(followership);
	}

	@Override
	public Optional<Followership> findByUserFollowerAndUserChecked(User userFollower, User userChecked) {
		return followershipRepository.findByUserFollowerAndUserChecked(userFollower, userChecked);
	}

	@Override
	public List<Followership> findAllById(Iterable<Long> ids) {
		return followershipRepository.findAllById(ids);
	}

	@Override
	public void deleteById(Long id) {
		followershipRepository.deleteById(id);
	}

	@Override
	public Integer findFollowersQuantity(Long idUser) {
		return followershipRepository.findFollowersQuantity(idUser);
	}

	@Override
	public Integer findFollowingQuantity(Long idUser) {
		return followershipRepository.findFollowingQuantity(idUser);
	}
	

}
