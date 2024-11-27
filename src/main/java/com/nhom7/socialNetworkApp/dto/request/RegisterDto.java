package com.nhom7.socialNetworkApp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

  private String email;
  private String username;
  private String password;
  private boolean isActive;

}
