package com.nhom7.socialNetworkApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message extends AbstractEntity<Long>{
    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","username","description",
            "avatar","status"})
    @JoinColumn(name = "user_transmitter_id", nullable = false)
    private User userTransmitter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","username","description",
            "avatar","status"})
    @JoinColumn(name = "user_receiver_id", nullable = false)
    private User userReceiver;

    @Column(name = "photo")
    private String photo;
}
