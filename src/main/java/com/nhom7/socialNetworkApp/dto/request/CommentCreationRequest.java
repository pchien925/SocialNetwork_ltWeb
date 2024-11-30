package com.nhom7.socialNetworkApp.dto.request;

import lombok.Getter;

@Getter
public class CommentCreationRequest {
    private String text;
    private long postId;
    private long userId;
}
