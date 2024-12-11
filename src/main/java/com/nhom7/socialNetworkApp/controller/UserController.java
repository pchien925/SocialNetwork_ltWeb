package com.nhom7.socialNetworkApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.model.ProfileModel;
import com.nhom7.socialNetworkApp.model.UserModel;
import com.nhom7.socialNetworkApp.services.IUserService;

import jakarta.servlet.http.HttpServlet;
import jakarta.validation.Valid;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/user")
public class UserController extends HttpServlet {
	static final long serialVersionUID = 1L;
	@Autowired
	private IUserService userService;
	@Autowired
    MessageSource messageSource;

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String Register()
	{
		return "/register";
	}
	@GetMapping("/sendOTP")
	public String sendOTP(@RequestParam(required = false) String message, 
	                                     @RequestParam(required = false) String email, 
	                                     ModelMap model) {
	    if (message != null) {
	        model.addAttribute("message", message);
	    }
	    if (email != null) {
	        model.addAttribute("email", email);
	    }
	    return "/sendOTP"; // Tên của file JSP
	}
	@GetMapping("/forgotpw")
	public String showForgotPasswordPage(@RequestParam(required = false) String message, 
	                                     @RequestParam(required = false) String email, 
	                                     ModelMap model) {
	    if (message != null) {
	        model.addAttribute("message", message);
	    }
	    if (email != null) {
	        model.addAttribute("email", email);
	    }
	    return "/forgotpw"; // Tên của file JSP
	}

	@GetMapping("/verify-account")
	public String verifyAccountPage(@RequestParam(required = false) String email, 
	                                 @RequestParam(required = false) String otp, 
	                                 ModelMap model) {
	    model.addAttribute("email", email);
	    model.addAttribute("otp", otp);
	    
	    String message=userService.verifyAccount(email, otp);
	    model.addAttribute("message",message);
	    return "confirmOTP"; 
	}
	

	@PostMapping("/register")
	public ModelAndView AddUser(@Valid @ModelAttribute("userModel") UserModel userModel, 
	                             BindingResult bindingResult, ModelMap model) {
	    // Kiểm tra lỗi validation từ userModel
	    if (bindingResult.hasErrors()) {
	    	System.out.println(bindingResult.getAllErrors());
	        return new ModelAndView("/register", model);
	    }
	    if (!userModel.getRepassword().equals(userModel.getPassword())) {
	        String message = "Error: Passwords do not match!";
	        model.addAttribute("message", message);
	        return new ModelAndView("/register", model);
	    } else if (userModel.getPassword().length() < 6) {
	        String message = "Error: Password must be longer than 6 characters!";
	        model.addAttribute("message", message);
	        return new ModelAndView("/register", model);
	    }
	    String message=userService.register(userModel);
	    model.addAttribute("message",message);
	    return new ModelAndView("redirect:/signin", model);
	}

	@PostMapping("/regenerate-otp")
	public ModelAndView regenerateOtp(@RequestParam String email, ModelMap model) {
	    String message = userService.regenerateOtp(email);

	    // Thêm giá trị email và thông báo vào model để hiển thị lại trên form
	    model.addAttribute("message", message);
	    model.addAttribute("email", email);

	    // Forward đến trang "forgotpw" mà không thay đổi URL
	    return new ModelAndView("/forgotpw", model);
	}
	@PostMapping("/forgotpw")
	public ModelAndView ForgotPassword(@Valid @ModelAttribute("userModel") UserModel userModel,
			ModelMap model) {
		if (!userModel.getRepassword().equals(userModel.getPassword())) {
	        String message = "Error: Passwords do not match!";
	        model.addAttribute("message", message);
	        return new ModelAndView("/forgotpw", model);
	    } else if (userModel.getPassword().length() < 6) {
	        String message = "Error: Password must be longer than 6 characters!";
	        model.addAttribute("message", message);
	        return new ModelAndView("/forgotpw", model);
	    }
	    String message=userService.ChangePassword(userModel.getEmail(),userModel.getPassword(),userModel.getOtp());
	    model.addAttribute("message",message);
	    return new ModelAndView("redirect:/login", model);
	}
	@RequestMapping("/user-list")
	public String all(Model model) {
		
		int count = (int) userService.count();
	    int currentPage = 1;
	    int pageSize = 5;
	    Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
	    Page<User> resultPage = null;
        resultPage = userService.findAll(pageable);
	    
	    int totalPages = resultPage.getTotalPages();
	    if (totalPages > 0) {
	        int start = Math.max(1, currentPage - 2);
	        int end = Math.min(currentPage + 2, totalPages);
	        if (totalPages > count) {
	            if (end == totalPages)
	                start = end - count;
	            else if (start == 1)
	                end = start + count;
	        }
	        List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	        System.out.println(pageNumbers.size());
	        model.addAttribute("pageNumbers", pageNumbers);
	    }
		model.addAttribute("userPage", resultPage);
		return "web/user-list";
	}
	@RequestMapping("/searchpaginated")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
	        @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
	    int count = (int) userService.count();
	    int currentPage = page.orElse(1);
	    int pageSize = size.orElse(3);
	    // Sử dụng 'name' thay vì 'categoryName' ở đây
	   // Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
	    Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
	    Page<User> resultPage = null;
	    
	    if (StringUtils.hasText(name)) {
	        resultPage = userService.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, pageable);
	        model.addAttribute("name", name);
	    } else {
	        resultPage = userService.findAll(pageable);
	    }
	    
	    int totalPages = resultPage.getTotalPages();
	    if (totalPages > 0) {
	        int start = Math.max(1, currentPage - 2);
	        int end = Math.min(currentPage + 2, totalPages);
	        if (totalPages > count) {
	            if (end == totalPages)
	                start = end - count;
	            else if (start == 1)
	                end = start + count;
	        }
	        List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	        System.out.println(pageNumbers.size());
	        model.addAttribute("pageNumbers", pageNumbers);
	    }
	    
	    model.addAttribute("userPage", resultPage);
	    return "web/user-list";
	}
	@GetMapping("/get/friends/{idUser}")
    public ResponseEntity<?> getFriends(@PathVariable Long idUser, Locale locale) {
        try {
            List<User> friends = userService.findFriendsByUser(idUser);
            return new ResponseEntity<>(friends, HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("error.database", null, locale));
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@GetMapping("/get/followers/{idUser}")
    public ResponseEntity<?> getFollowers(@PathVariable Long idUser, Locale locale) {
        try {
            List<User> followers = userService.findFollowersByUser(idUser);
            return new ResponseEntity<>(followers, HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("error.database", null, locale));
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@GetMapping("/get/following/{idUser}")
    public ResponseEntity<?> getFollowing(@PathVariable Long idUser, Locale locale) {
        try {
            List<User> following = userService.findFollowingByUser(idUser);
            return new ResponseEntity<>(following, HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("error.database", null, locale));
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	@DeleteMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteUsers(@PathVariable Long idUser,Locale locale) {
        Map<String, Object> response = new HashMap<>();
        try {
            userService.deleteById(idUser);
            response.put("message", messageSource.getMessage("userController.deletionProcess", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", response.put("message", messageSource.getMessage("error.databaseOrFile", null, locale)));
            response.put("error", e.getMessage() + ": " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
