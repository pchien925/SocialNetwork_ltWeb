package com.nhom7.socialNetworkApp.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ProfileModel implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String phone;
    private String email;
    private Date createdAt;
    @NotEmpty(message="Khong duoc bo trong")	
    private Boolean isFriendOfUser;
    
}