package com.nhom7.socialNetworkApp.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class MediaResponse {
    private Long id;

    private String mediaKey;

    private String caption;

    private String type;

    private String url;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
