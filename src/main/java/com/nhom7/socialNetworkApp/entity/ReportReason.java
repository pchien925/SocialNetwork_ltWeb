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
@Table(name = "report_reason")
public class ReportReason extends AbstractEntity<Integer>{
    @Column(name = "reason", nullable = false)
    private String reason;
}
