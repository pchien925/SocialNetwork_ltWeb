package com.nhom7.socialNetworkApp.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom7.socialNetworkApp.entity.Friendship;
import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.entity.notification.Notification;
import com.nhom7.socialNetworkApp.entity.notification.NotificationFollowership;
import com.nhom7.socialNetworkApp.entity.notification.NotificationFriendship;
import com.nhom7.socialNetworkApp.repository.NotificationRepository;
import com.nhom7.socialNetworkApp.services.INotificationService;

@Service
public class NotificationServiceImpl implements INotificationService{
	@Autowired
	NotificationRepository notificationRepository;
	
	
	@Override
	public <S extends Notification> S save(S entity) {
		return notificationRepository.save(entity);
	}


	@Override
	public List<Notification> findByUserReceiver(User userReceiver) {
		return notificationRepository.findByUserReceiver(userReceiver);
	}
	

}
