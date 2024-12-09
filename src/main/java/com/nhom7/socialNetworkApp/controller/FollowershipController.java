package com.nhom7.socialNetworkApp.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhom7.socialNetworkApp.entity.Followership;
import com.nhom7.socialNetworkApp.entity.Friendship;
import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.entity.notification.NotificationFollowership;
import com.nhom7.socialNetworkApp.entity.notification.NotificationFriendship;
import com.nhom7.socialNetworkApp.services.IFollowershipService;
import com.nhom7.socialNetworkApp.services.INotificationService;
import com.nhom7.socialNetworkApp.services.IUserService;

import jakarta.servlet.http.HttpServlet;

@Controller
@RequestMapping("/user")
public class FollowershipController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Autowired
	private IUserService userService;
	@Autowired
	private INotificationService notificationService;
	@Autowired
	private IFollowershipService followershipService;
	@Autowired
	MessageSource messageSource;
	@PostMapping("/api/followerships/follow")
	@ResponseBody // Trả về JSON
	public Map<String, String> addFollow(@RequestParam(required = false) String userId, Principal principal) {
	    Map<String, String> response = new HashMap<>();

	    // Lấy người gửi từ phiên đăng nhập
	    //String currentUsername = principal.getName();
	    //Optional<User> userTransmitterOpt = userService.findByUsername(currentUsername);
	    Optional<User> userFollowerOpt = userService.findById(3l);
	    
	    if (userId == null || userId.isBlank()) {
	        response.put("status", "error");
	        response.put("message", "Invalid user ID.");
	        return response;
	    }

	    Optional<User> userCheckedOpt;
	    try {
	        userCheckedOpt = userService.findById(Long.parseLong(userId));
	    } catch (NumberFormatException e) {
	        response.put("status", "error");
	        response.put("message", "Invalid user ID format.");
	        return response;
	    }

	    if (userFollowerOpt.isPresent() && userCheckedOpt.isPresent()) {
	        User userFollower = userFollowerOpt.get();
	        User userChecked= userCheckedOpt.get();

	        followershipService.addFollower(userFollower, userChecked);
	        Optional<Followership> addedFollowership= followershipService.findByUserFollowerAndUserChecked(userFollower, userChecked);
	        NotificationFollowership notificationFriendship = new NotificationFollowership(userChecked , addedFollowership.get());
	        notificationFriendship.setIsViewed(false);
            notificationService.save(notificationFriendship);
	        response.put("status", "success");
	        response.put("message", "Follow added successfully!");
	    } else {
	        response.put("status", "error");
	        response.put("message", "One or both users do not exist.");
	    }
	    return response;
	}
	@DeleteMapping("/followership/delete/{idFollowership}")
    public ResponseEntity<?> deleteFollowership(@PathVariable Long idFollowership, Locale locale) {
        Map<String, Object> response = new HashMap<>();
        try {
            followershipService.deleteById(idFollowership);
            response.put("message", messageSource.getMessage("followershipController.deleteFollowership", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("message", messageSource.getMessage("error.database", null, locale));
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@GetMapping("/get/followers-count/{idUser}")
    public ResponseEntity<?> getFollowersCount(@PathVariable Long idUser, Locale locale) {
        try {
            return new ResponseEntity<>(followershipService.findFollowersQuantity(idUser), HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("error.database", null, locale));
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get/following-count/{idUser}")
    public ResponseEntity<?> getFollowingCount(@PathVariable Long idUser, Locale locale) {
        try {
            return new ResponseEntity<>(followershipService.findFollowingQuantity(idUser), HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("error.database", null, locale));
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
