package com.nhom7.socialNetworkApp.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateUserRequest {
    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    private String phone;

    private String email;

    private String avatar;

    private String description;
}
