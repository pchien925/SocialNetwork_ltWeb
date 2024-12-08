package com.nhom7.socialNetworkApp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhom7.socialNetworkApp.entity.Comment;
import com.nhom7.socialNetworkApp.entity.Like;
import com.nhom7.socialNetworkApp.entity.Report;
import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.entity.notification.NotificationLikePost;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class PostResponse {
    private Long id;

    private String text;

    private String photo;

    private User user;

    private String created;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
