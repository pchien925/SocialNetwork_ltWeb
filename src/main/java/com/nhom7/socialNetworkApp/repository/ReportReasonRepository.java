package com.nhom7.socialNetworkApp.repository;

import com.nhom7.socialNetworkApp.entity.ReportReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportReasonRepository extends JpaRepository<ReportReason, Long> {
}
