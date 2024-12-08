package com.nhom7.socialNetworkApp.dto.response;

import com.nhom7.socialNetworkApp.entity.Post;
import com.nhom7.socialNetworkApp.entity.ReportReason;
import com.nhom7.socialNetworkApp.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReportReponse {

    private Long id;
    private String extraInformation;
    private Post post;
    private ReportReason reportReason;
    private User user;
}
