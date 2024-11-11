package com.nhom7.socialNetworkApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "status")
public class Status extends AbstractEntity<Long>{
    @Column(name = "text", nullable = false)
    private String text;
}
