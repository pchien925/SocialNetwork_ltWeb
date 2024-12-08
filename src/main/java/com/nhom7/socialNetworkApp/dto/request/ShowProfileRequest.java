package com.nhom7.socialNetworkApp.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ShowProfileRequest {
	@NotNull
	private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String phone;
    private String email;
    private LocalDate createdAt;
    private Boolean isFriendOfUser;
}
