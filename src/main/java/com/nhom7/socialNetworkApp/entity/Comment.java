package com.nhom7.socialNetworkApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends AbstractEntity<Long>{
    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description",
            "status"})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","text","photo",
            "date","user","likes","reports"})
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "replies", joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "reply_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"comment_id", "reply_id"})})
    private List<Comment> replies;

    public boolean getHasReplies() {
        return this.replies.size() > 0;
    }

}
