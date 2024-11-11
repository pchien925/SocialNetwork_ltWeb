package com.nhom7.socialNetworkApp.entity.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhom7.socialNetworkApp.entity.Comment;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@DiscriminatorValue("comment_type")
@NoArgsConstructor
public class NotificationComment extends Notification {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","text","post","date","hasReplies"})
    @JoinColumn(name = "comment_id")
    private Comment comment;


    @Override
    public String getType() {
        return "comment_type";
    }
}