package com.nhom7.socialNetworkApp.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhom7.socialNetworkApp.services.IUserService;

import jakarta.servlet.http.HttpServlet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/")
public class LoginController extends HttpServlet {
	static final long serialVersionUID = 1L;
	@Autowired
	IUserService userService;
	
	@GetMapping("/login")
	public String Login()
	{
		return "/login";
	}
	@PostMapping("/login")
	public String postMethodName(@RequestBody String entity) {
		//TODO: process POST request
		
		return "/login";
	}
	
}
