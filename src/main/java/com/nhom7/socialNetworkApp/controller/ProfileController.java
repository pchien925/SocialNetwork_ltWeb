package com.nhom7.socialNetworkApp.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.model.ProfileModel;
import com.nhom7.socialNetworkApp.services.IUserService;

import jakarta.servlet.http.HttpServlet;

@Controller
@RequestMapping("/")
public class ProfileController extends HttpServlet {
	static final long serialVersionUID = 1L;
	@Autowired
	private IUserService userService;

	@GetMapping("/profile/{id}")
	public ModelAndView profile(ModelMap model, @PathVariable("id") Long userId) {
		Optional<User> optUser = userService.findById(userId);
		if (optUser.isPresent()) {
			User entity = optUser.get();
			ProfileModel profileModel = new ProfileModel();
			// Sao chép các thuộc tính từ entity sang ProfileModel
			BeanUtils.copyProperties(entity, profileModel);

			// Kiểm tra xem giá trị có được sao chép đúng không
			System.out.println("Profile ID after copy: " + profileModel.getId());

			model.addAttribute("profile", profileModel);
			return new ModelAndView("/web/profile", model);
		} else {
			model.addAttribute("message", "User is not existed!!!");
			return new ModelAndView("forward:/web/home", model);
		}
	}

	@GetMapping("/profile")
	public String profile() {
		return "web/updateProfile";
	}


}
