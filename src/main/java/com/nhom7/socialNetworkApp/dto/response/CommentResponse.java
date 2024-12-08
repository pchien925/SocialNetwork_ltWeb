package com.nhom7.socialNetworkApp.dto.response;

import com.nhom7.socialNetworkApp.entity.Comment;
import com.nhom7.socialNetworkApp.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CommentResponse {
    private long id;
    private String text;
    private long postId;
    private User user;
    private Comment parent;
    private String created;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
