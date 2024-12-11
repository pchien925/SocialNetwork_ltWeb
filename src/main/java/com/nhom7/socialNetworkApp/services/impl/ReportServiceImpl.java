package com.nhom7.socialNetworkApp.services.impl;

import com.nhom7.socialNetworkApp.dto.request.ReportRequest;
import com.nhom7.socialNetworkApp.dto.response.ReportReponse;
import com.nhom7.socialNetworkApp.entity.Post;
import com.nhom7.socialNetworkApp.entity.Report;
import com.nhom7.socialNetworkApp.entity.ReportReason;
import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.mapper.ReportMapper;
import com.nhom7.socialNetworkApp.repository.PostRepository;
import com.nhom7.socialNetworkApp.repository.ReportReasonRepository;
import com.nhom7.socialNetworkApp.repository.ReportRepository;
import com.nhom7.socialNetworkApp.repository.UserRepository;
import com.nhom7.socialNetworkApp.services.IReportSerice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements IReportSerice {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final ReportReasonRepository reportReasonRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;



    @Override
    public ReportReponse createReport(ReportRequest reportRequest) {
        Post post = postRepository.findById(reportRequest.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
        //User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new RuntimeException("User not found"));
        User user = userRepository.findById(reportRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        ReportReason reportReason = reportReasonRepository.findById(Long.valueOf(reportRequest.getReportReasonId()))
                .orElseThrow(() -> new RuntimeException("Report reason not found"));

        Report report = Report.builder()
                .post(post)
                .user(user)
                .reportReason(reportReason)
                .extraInformation(reportRequest.getExtraInformation())
                .build();
        // Lưu báo cáo vào cơ sở dữ liệu
        Report savedReport = reportRepository.save(report);

        // Trả về thông tin báo cáo đã tạo
        return reportMapper.toReportReponse(savedReport);

    }

    @Override
    public ReportReponse get(Long reportId) {
        return reportMapper.toReportReponse(reportRepository.findById(reportId).orElseThrow(
                () -> new RuntimeException("Report Not Found")
        ));
    }

    @Override
    public List<ReportReponse> getAllReports()
    {
        List<Report> reports = reportRepository.findAll();

        return reports.stream()
                .map(reportMapper::toReportReponse)  // Convert Report to ReportReponse
                .collect(Collectors.toList());  // Collect them into a List of ReportReponse
    }


    @Override
    public Page<ReportReponse> getReportsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);  // Set the page and page size
        Page<Report> reportPage = reportRepository.findAll(pageable);  // Fetch paginated results

        // Convert Page of Report entities to Page of ReportReponse DTOs
        return reportPage.map(reportMapper::toReportReponse);  // Map each Report to ReportReponse
    }
}
