package com.nhom7.socialNetworkApp.dto.request;


import lombok.Getter;

@Getter
public class ReportRequest {
    private Long reportId;       // ID of the report
    private Long postId;         // ID of the post related to the report
    private Long userId;         // ID of the user who created the report
    private Integer reportReasonId; // Reason for the report
    private String extraInformation; // Additional details for the report
}
