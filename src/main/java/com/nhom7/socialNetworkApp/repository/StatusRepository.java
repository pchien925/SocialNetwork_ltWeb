package com.nhom7.socialNetworkApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhom7.socialNetworkApp.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{

}
