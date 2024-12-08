package com.nhom7.socialNetworkApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.entity.notification.Notification;
import com.nhom7.socialNetworkApp.entity.notification.NotificationFollowership;
import com.nhom7.socialNetworkApp.entity.notification.NotificationFriendship;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

	List<Notification> findByUserReceiver(User userReceiver);
}
