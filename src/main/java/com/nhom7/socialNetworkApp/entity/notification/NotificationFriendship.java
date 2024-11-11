package com.nhom7.socialNetworkApp.entity.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhom7.socialNetworkApp.entity.Friendship;
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
@DiscriminatorValue("friendship_type")
@NoArgsConstructor
public class NotificationFriendship extends Notification {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","idUser","username","photo",
            "description","creationDate","deletionDate","isChecked","userReceiver","date","status"})
    @JoinColumn(name = "friendship_id")
    private Friendship friendship;

    @Override
    public String getType() {
        return "friendship_type";
    }
}