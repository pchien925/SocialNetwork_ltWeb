package com.nhom7.socialNetworkApp.dto.request;

import lombok.Getter;

@Getter
public class LikeRequest {
    private long postId;
    private long userId;
}
