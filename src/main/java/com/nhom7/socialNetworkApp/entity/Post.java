package com.nhom7.socialNetworkApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhom7.socialNetworkApp.entity.notification.NotificationLikePost;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post extends AbstractEntity<Long>{
    @Column(name = "text")
    private String text;

    @Column(name = "photo")
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description","status"})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy="post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","post","name","surname","photo",
            "description","isChecked","status"})
    private List<Like> likes;

    @OneToMany(mappedBy="post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy="post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<NotificationLikePost> notifications;

    @OneToMany(mappedBy="post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","post","reportReason","extraInformation"})
    private List<Report> reports;
}
