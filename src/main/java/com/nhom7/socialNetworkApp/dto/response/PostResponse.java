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

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class PostResponse {
    private String text;

    private String photo;

    private User user;

    @Builder.Default
    private List<Like> likes = new ArrayList<>();

    @Builder.Default
    private List<Comment> comments = new ArrayList<>();
}
