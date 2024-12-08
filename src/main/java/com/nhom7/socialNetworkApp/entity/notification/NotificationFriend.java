package com.nhom7.socialNetworkApp.entity.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhom7.socialNetworkApp.entity.Followership;
import com.nhom7.socialNetworkApp.entity.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@DiscriminatorValue("friend_type")
@NoArgsConstructor
public class NotificationFriend extends Notification {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","username","description",
            "creationDate","deletionDate","photo","isChecked","status"})
    @JoinColumn(name = "user_friend_id")
    private User friend;
    public NotificationFriend(User userReceiver, User friend) {
    	this.setIsViewed(false);
        this.setUserReceiver(userReceiver);
        this.friend = friend;
    }
    @Override
    public String getType() {
        return "friend_type";
    }
}