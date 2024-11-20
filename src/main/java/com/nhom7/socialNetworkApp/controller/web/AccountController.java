package com.nhom7.socialNetworkApp.controller.web;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhom7.socialNetworkApp.entity.Message;
import com.nhom7.socialNetworkApp.entity.Role;
import com.nhom7.socialNetworkApp.entity.Status;
import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.model.UserModel;
import com.nhom7.socialNetworkApp.services.IUserService;

import jakarta.servlet.http.HttpServlet;
import jakarta.validation.Valid;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@Controller
@RequestMapping("/")
public class AccountController extends HttpServlet {
	static final long serialVersionUID = 1L;
	@Autowired
	private IUserService userService;

	
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

	@PostMapping("/addUser")
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
	    return new ModelAndView("redirect:/login", model);
	}
//	@PutMapping("/verify-account")
//	  public ResponseEntity<String> verifyAccount(@RequestParam String email,
//	      @RequestParam String otp) {
//		System.out.println(email);
//		System.out.println(otp);
//	    return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
//	  }
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

	  /*@PutMapping("/regenerate-otp")
	  public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
	    return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
	  }*/
}
