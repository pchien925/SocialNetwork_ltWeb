package com.nhom7.socialNetworkApp.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nhom7.socialNetworkApp.entity.Friendship;
import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.entity.notification.NotificationFriend;
import com.nhom7.socialNetworkApp.entity.notification.NotificationFriendship;
import com.nhom7.socialNetworkApp.model.UserModel;
import com.nhom7.socialNetworkApp.services.IFriendshipService;
import com.nhom7.socialNetworkApp.services.INotificationService;
import com.nhom7.socialNetworkApp.services.IUserService;

import jakarta.servlet.http.HttpServlet;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/user")
public class FriendController extends HttpServlet{
	static final long serialVersionUID = 1L;
	@Autowired
	private IUserService userService;
	@Autowired
	private INotificationService notificationService;
	@Autowired
	private IFriendshipService friendshipService;
	@Autowired
    MessageSource messageSource;
	@PostMapping("/api/sendFriendRequest")
	@ResponseBody // Trả về JSON
	public Map<String, String> addFriend(@RequestParam(required = false) String userId, Principal principal) {
	    Map<String, String> response = new HashMap<>();

	    // Lấy người gửi từ phiên đăng nhập
	    //String currentUsername = principal.getName();
	    //Optional<User> userTransmitterOpt = userService.findByUsername(currentUsername);
	    Optional<User> userTransmitterOpt = userService.findById(3l);
	    
	    if (userId == null || userId.isBlank()) {
	        response.put("status", "error");
	        response.put("message", "Invalid user ID.");
	        return response;
	    }

	    Optional<User> userReceiverOpt;
	    try {
	        userReceiverOpt = userService.findById(Long.parseLong(userId));
	    } catch (NumberFormatException e) {
	        response.put("status", "error");
	        response.put("message", "Invalid user ID format.");
	        return response;
	    }

	    if (userTransmitterOpt.isPresent() && userReceiverOpt.isPresent()) {
	        User userTransmitter = userTransmitterOpt.get();
	        User userReceiver = userReceiverOpt.get();

	        friendshipService.addFriend(userTransmitter, userReceiver);
	        Optional<Friendship> addedFriendship= friendshipService.findByUsers(userTransmitter, userReceiver);
	        NotificationFriendship notificationFriendship = new NotificationFriendship(userReceiver, addedFriendship.get());
	        notificationFriendship.setIsViewed(false);
            notificationService.save(notificationFriendship);
	        response.put("status", "success");
	        response.put("message", "Friend added successfully!");
	    } else {
	        response.put("status", "error");
	        response.put("message", "One or both users do not exist.");
	    }
	    return response;
	}
	@PutMapping("/put/accept-request/{idFriendship}")
    public ResponseEntity<?> acceptFriendRequest(@PathVariable Long idFriendship, Locale locale) {
        Map<String, Object> response = new HashMap<>();
        try {
            Friendship friendship = friendshipService.findById(idFriendship).get();
            friendship.setIsAccept(true);
            friendship.setUpdatedAt(LocalDateTime.now());

            friendship = friendshipService.save(friendship);
            NotificationFriend notificationFriend = new NotificationFriend(friendship.getUserTransmitter(), friendship.getUserReceiver());
            notificationService.save(notificationFriend);
            response.put("message", friendship.getUserTransmitter().getFullName() + " " +
                    messageSource.getMessage("friendshipController.acceptFriendRequest", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            //response.put("message", messageSource.getMessage("error.userNotExist", null, locale));
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
