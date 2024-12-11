package com.nhom7.socialNetworkApp.services;

import com.nhom7.socialNetworkApp.dto.request.ReportRequest;
import com.nhom7.socialNetworkApp.dto.response.ReportReponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IReportSerice {



    public ReportReponse createReport(ReportRequest reportRequest);
    public ReportReponse get(Long reportId);
    public List<ReportReponse> getAllReports();
    public Page<ReportReponse> getReportsWithPagination(int page, int size);
    public long countReports();

    public void deleteReport(Long reportId);


}
