package com.nhom7.socialNetworkApp.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserListRespone {
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    private String phone;

    private String email;

    private String username;

    private String password;

    private String avatar;

    private String description;

    private Boolean isActive;
}
