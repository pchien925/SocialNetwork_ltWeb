package com.nhom7.socialNetworkApp.mapper;

import com.nhom7.socialNetworkApp.dto.request.ReportRequest;
import com.nhom7.socialNetworkApp.dto.response.ReportReponse;
import com.nhom7.socialNetworkApp.entity.Report;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {
    Report toReport(ReportRequest request);
    ReportReponse toReportReponse(Report report);
}
