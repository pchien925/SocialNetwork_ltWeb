package com.nhom7.socialNetworkApp.controller.api;

import com.nhom7.socialNetworkApp.dto.request.MediaRequest;
import com.nhom7.socialNetworkApp.dto.response.ApiResponse;
import com.nhom7.socialNetworkApp.dto.response.MediaResponse;
import com.nhom7.socialNetworkApp.services.IMediaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/medias")
public class MediaRestController {
    private final IMediaService mediaService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ApiResponse<MediaResponse> save(@ModelAttribute @Valid MediaRequest request){
        return ApiResponse.<MediaResponse>builder()
                .status(HttpStatus.CREATED.value())
                .data(mediaService.saveMedia(request))
                .build();
    }
}
