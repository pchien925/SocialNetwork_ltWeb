package com.nhom7.socialNetworkApp.dto.response;

import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfileResponse {
	@NotNull
	private Long id;
    private String firstName;
    private String lastName;
    private String avatar;
    private LocalDate dateOfBirth;
    private String gender;
    private String phone;
    private String email;
    private LocalDate createdAt;
    private Boolean isFriendOfUser;
}
