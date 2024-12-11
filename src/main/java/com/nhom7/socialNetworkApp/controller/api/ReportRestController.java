package com.nhom7.socialNetworkApp.controller.api;


import com.nhom7.socialNetworkApp.dto.request.ReportRequest;
import com.nhom7.socialNetworkApp.dto.response.ApiResponse;
import com.nhom7.socialNetworkApp.dto.response.ReportReponse;
import com.nhom7.socialNetworkApp.services.IPostService;
import com.nhom7.socialNetworkApp.services.IReportSerice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mod/reports")
@Slf4j
public class ReportRestController {
    private final IReportSerice reportSerice;
    private final IPostService postService;
    @PostMapping("/delete")
    public ApiResponse<Void> deleteReportAndPost(@RequestBody ReportRequest reportRequest) {
        reportSerice.deleteReport(reportRequest.getReportId());
        postService.delete(reportRequest.getPostId());
        return ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Post and report deleted successfully")
                .build();
    }


    @GetMapping("/keep")
    public ApiResponse<Void> keepPost(@RequestParam Long reportId) {
        // Only delete the report
        reportSerice.deleteReport(reportId);
        return ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Report deleted, post kept successfully")
                .build();
    }

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
    public ApiResponse<Map<String, Object>> getReports(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        Page<ReportReponse> reportsPage = reportSerice.getReportsWithPagination(page, size);
        List<ReportReponse> reports = reportsPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("reports", reports);
        response.put("totalPages", reportsPage.getTotalPages());
        response.put("totalElements", reportsPage.getTotalElements());
        response.put("currentPage", reportsPage.getNumber());

        return ApiResponse.<Map<String, Object>>builder()
                .status(HttpStatus.OK.value())
                .data(response)
                .build();
    }


}
