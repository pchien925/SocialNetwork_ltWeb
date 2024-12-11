package com.nhom7.socialNetworkApp.services;

import com.nhom7.socialNetworkApp.dto.request.MediaRequest;
import com.nhom7.socialNetworkApp.dto.response.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IMediaService {
    MediaResponse saveMedia(MediaRequest requestDTO);

    MediaResponse upload(MultipartFile multipartFile);
}
