package com.nhom7.socialNetworkApp.entity.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhom7.socialNetworkApp.entity.Post;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@DiscriminatorValue("like_post_type")
@NoArgsConstructor
public class NotificationLikePost extends Notification {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","text","photo","date","user","likes"})
    @JoinColumn(name = "post_id")
    private Post post;

    @Override
    public String getType() {
        return "like_post_type";
    }
}