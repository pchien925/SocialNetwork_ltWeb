package com.nhom7.socialNetworkApp.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhom7.socialNetworkApp.dto.response.ApiResponse;
import com.nhom7.socialNetworkApp.dto.response.ProfileResponse;
import com.nhom7.socialNetworkApp.services.IUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles")
public class ProfileRestController {
	
	private final IUserService userService;
	
	@GetMapping("/{userId}")
	ApiResponse<ProfileResponse> getPost(@PathVariable Long userId) {
        return ApiResponse.<ProfileResponse>builder()
                .status(HttpStatus.OK.value())
                .data(userService.getProfile(userId))
                .build();
    }
	
}
