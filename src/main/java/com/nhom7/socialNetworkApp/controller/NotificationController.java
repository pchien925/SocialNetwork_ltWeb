package com.nhom7.socialNetworkApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.entity.notification.Notification;
import com.nhom7.socialNetworkApp.model.ProfileModel;
import com.nhom7.socialNetworkApp.services.INotificationService;
import com.nhom7.socialNetworkApp.services.IUserService;

import jakarta.servlet.http.HttpServlet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/")
public class NotificationController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Autowired
	private IUserService userService;
	@Autowired
	private INotificationService notificationService;
	@GetMapping("/api/notification/{userId}") 
	public ResponseEntity<?> getNotifications(@PathVariable("userId") Long userId) {
	    Optional<User> optUser = userService.findById(userId);
	    
	    if (optUser.isEmpty()) {
	        return ResponseEntity.badRequest().body("User does not exist!");
	    }
	    
	    List<Notification> notifications = notificationService.findByUserReceiver(optUser.get());
	    return ResponseEntity.ok(notifications);
	}

	@GetMapping("/notification/13")
		public String notification() {
			return "web/notification";
		}


	
}
