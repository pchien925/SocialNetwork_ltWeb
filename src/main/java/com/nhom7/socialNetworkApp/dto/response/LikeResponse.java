package com.nhom7.socialNetworkApp.dto.response;

import com.nhom7.socialNetworkApp.entity.User;
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
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
