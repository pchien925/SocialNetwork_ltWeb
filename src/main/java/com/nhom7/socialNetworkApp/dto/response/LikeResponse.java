package com.nhom7.socialNetworkApp.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class LikeResponse {
    private long id;
    private long postId;
    private long userId;
    private String username;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
