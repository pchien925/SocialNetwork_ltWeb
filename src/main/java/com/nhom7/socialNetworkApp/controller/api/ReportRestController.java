package com.nhom7.socialNetworkApp.controller.api;


import com.nhom7.socialNetworkApp.dto.request.ReportRequest;
import com.nhom7.socialNetworkApp.dto.response.ApiResponse;
import com.nhom7.socialNetworkApp.dto.response.ReportReponse;
import com.nhom7.socialNetworkApp.services.IReportSerice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mod/reports")
@Slf4j
public class ReportRestController {
    private final IReportSerice reportSerice;
    @GetMapping("/{reportId}")
    public ApiResponse<ReportReponse> getReportByReportId(@PathVariable Long reportId)
    {
        return ApiResponse.<ReportReponse>builder()
                .status(HttpStatus.OK.value())
                .data(reportSerice.get(reportId))
                .build();
    }

    @PostMapping("/create")
    public ApiResponse<ReportReponse> createReport(@RequestBody ReportRequest reportRequest) {
        ReportReponse reportReponse = reportSerice.createReport(reportRequest);

        return ApiResponse.<ReportReponse>builder()
                .status(HttpStatus.OK.value())
                .data(reportReponse)
                .build();
    }


    @GetMapping("/all")
    public ApiResponse<List<ReportReponse>> getReports(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Page<ReportReponse> reportsPage = reportSerice.getReportsWithPagination(page, size);
        List<ReportReponse> reports = reportsPage.getContent(); // Extract only the list of reports

        return ApiResponse.<List<ReportReponse>>builder()
                .status(HttpStatus.OK.value())
                .data(reports) // Return only the list of reports
                .build();
    }

}
