package com.nhom7.socialNetworkApp.controller.api;

import com.nhom7.socialNetworkApp.dto.request.UpdateUserRequest;
import com.nhom7.socialNetworkApp.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nhom7.socialNetworkApp.dto.response.ApiResponse;
import com.nhom7.socialNetworkApp.dto.response.ProfileResponse;
import com.nhom7.socialNetworkApp.services.IUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;


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

	@GetMapping("/my-profile")
	ApiResponse<UserResponse> getMyProfile() {
		return ApiResponse.<UserResponse>builder()
				.status(HttpStatus.OK.value())
				.data(userService.getMyProfile())
				.build();
	}

	@PutMapping("/my-profile")
	ApiResponse<UserResponse> updateMyProfile(@RequestBody UpdateUserRequest request) {
		return ApiResponse.<UserResponse>builder()
				.status(HttpStatus.OK.value())
				.data(userService.updateMyProfile(request))
				.build();
	}
	
}
