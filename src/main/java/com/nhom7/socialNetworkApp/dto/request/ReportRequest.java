package com.nhom7.socialNetworkApp.dto.request;


import lombok.Getter;

@Getter
public class ReportRequest {
    private Long postId;

    private Long userId;

    private Integer reportReasonId;


    private String extraInformation;
}
