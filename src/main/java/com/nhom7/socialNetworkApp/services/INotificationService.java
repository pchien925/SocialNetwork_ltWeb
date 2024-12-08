package com.nhom7.socialNetworkApp.services;

import java.util.List;
import java.util.Optional;

import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.entity.notification.Notification;

public interface INotificationService {

	<S extends Notification> S save(S entity);

	List<Notification> findByUserReceiver(User userReceiver);


}
