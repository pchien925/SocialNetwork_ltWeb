package com.nhom7.socialNetworkApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhom7.socialNetworkApp.entity.notification.NotificationFriendship;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "friendship")
public class Friendship extends AbstractEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description","isActive"})
    @JoinColumn(name = "user_transmitter_id", nullable = false)
    private User userTransmitter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description", "isActive"})
    @JoinColumn(name = "user_receiver_id", nullable = false)
    private User userReceiver;

    @Column(name = "is_accept",columnDefinition = "BOOLEAN", nullable = false)
    private Boolean isAccept;

    @OneToMany(mappedBy="friendship", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<NotificationFriendship> notifications;
}
