package com.nhom7.socialNetworkApp.mapper;

import com.nhom7.socialNetworkApp.dto.response.UserListRespone;
import com.nhom7.socialNetworkApp.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserListMapper {

    UserListRespone toUserListRespone(User user);
}
