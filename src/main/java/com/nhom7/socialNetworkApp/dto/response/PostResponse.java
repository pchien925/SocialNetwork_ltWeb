package com.nhom7.socialNetworkApp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhom7.socialNetworkApp.entity.Comment;
import com.nhom7.socialNetworkApp.entity.Like;
import com.nhom7.socialNetworkApp.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PostResponse {
    private Long id;

    private String text;

    private String photo;

    private User user;

    private List<Like> likes;

    private List<Comment> comments;
}
