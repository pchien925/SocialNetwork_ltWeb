package com.nhom7.socialNetworkApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhom7.socialNetworkApp.entity.notification.Notification;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User extends AbstractEntity<Long> {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    
    @Column(name = "otp")
    private String otp;
    @Column(name = "otpGeneratedTime")
    private LocalDateTime otpGeneratedTime;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean isActive;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    @JsonIgnore
    private Status status;

    @OneToMany(mappedBy="userReceiver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Notification> notifications;

    @OneToMany(mappedBy="userTransmitter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Friendship> friendshipsTransmitted;

    @OneToMany(mappedBy="userReceiver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Friendship> friendshipsReceived;

    @OneToMany(mappedBy="userChecked", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Followership> following;

    @OneToMany(mappedBy="userFollower", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Followership> followers;

    @OneToMany(mappedBy="userTransmitter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> messagesTransmitted;

    @OneToMany(mappedBy="userReceiver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Message> messagesReceived;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Like> likes;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Report> reports;
}
