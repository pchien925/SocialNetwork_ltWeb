package com.nhom7.socialNetworkApp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ReplyCommentRequest {
    private String text;
    private long commentId;
}
