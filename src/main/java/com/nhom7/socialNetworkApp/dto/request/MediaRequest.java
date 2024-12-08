package com.nhom7.socialNetworkApp.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class MediaRequest {
    private String caption;

    private MultipartFile file;
}
