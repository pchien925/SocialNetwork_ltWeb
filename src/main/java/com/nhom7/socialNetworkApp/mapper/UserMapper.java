package com.nhom7.socialNetworkApp.mapper;

import com.nhom7.socialNetworkApp.dto.request.UpdateUserRequest;
import com.nhom7.socialNetworkApp.dto.response.UserResponse;
import org.mapstruct.Mapper;

import com.nhom7.socialNetworkApp.dto.response.ProfileResponse;
import com.nhom7.socialNetworkApp.entity.User;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
	ProfileResponse toProfileResponse(User profile);
	UserResponse toUserResponse(User user);

	void updateMyProfile(@MappingTarget User user, UpdateUserRequest request);
}
