package com.nhom7.socialNetworkApp.entity.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhom7.socialNetworkApp.entity.Followership;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@DiscriminatorValue("followership_type")
@NoArgsConstructor
public class NotificationFollowership extends Notification {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","idUser","username","photo",
            "description","creationDate","deletionDate","isChecked","userReceiver","date","status"})
    @JoinColumn(name = "followership_id")
    private Followership followership;

    @Override
    public String getType() {
        return "followership_type";
    }
}