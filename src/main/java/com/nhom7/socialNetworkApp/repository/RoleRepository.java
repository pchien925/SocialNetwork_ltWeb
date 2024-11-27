package com.nhom7.socialNetworkApp.repository;

import com.nhom7.socialNetworkApp.entity.Role;
import com.nhom7.socialNetworkApp.entity.enumeration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName name);
}
