package com.nhom7.socialNetworkApp.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostCreationRequest {
    private String text;
    private String photo;

    @NotNull
    private Long userId;
}
