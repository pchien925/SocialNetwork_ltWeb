package com.nhom7.socialNetworkApp.mapper;

import org.mapstruct.Mapper;

import com.nhom7.socialNetworkApp.dto.response.ProfileResponse;
import com.nhom7.socialNetworkApp.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	ProfileResponse toProfileResponse(User profile);

}
