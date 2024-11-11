package com.nhom7.socialNetworkApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhom7.socialNetworkApp.entity.notification.NotificationFollowership;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "followership")
public class Followership extends AbstractEntity<Long>{
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description","isActive"})
    @JoinColumn(name = "user_checked_id", nullable = false)
    private User userChecked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description","isActive"})
    @JoinColumn(name = "user_follower_id", nullable = false)
    private User userFollower;

    @OneToMany(mappedBy="followership", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<NotificationFollowership> notifications;

}
