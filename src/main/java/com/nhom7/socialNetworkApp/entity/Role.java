package com.nhom7.socialNetworkApp.entity;

import com.nhom7.socialNetworkApp.entity.enumeration.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role extends AbstractEntity<Integer>{
    @Enumerated(EnumType.STRING)
    private RoleName name;
}
