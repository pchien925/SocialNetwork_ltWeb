package com.nhom7.socialNetworkApp.entity.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhom7.socialNetworkApp.entity.Post;
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
@DiscriminatorValue("message_type")
@NoArgsConstructor
public class NotificationMessage extends Notification {
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","username","description",
            "creationDate","deletionDate","photo","isChecked","status"})
    @JoinColumn(name = "user_message_transmitter_id")
    private User userTransmitter;

    @Override
    public String getType() {
        return "message_type";
    }
}