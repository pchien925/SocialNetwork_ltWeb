package com.nhom7.socialNetworkApp.services;

import com.nhom7.socialNetworkApp.dto.request.MediaRequest;
import com.nhom7.socialNetworkApp.dto.response.MediaResponse;

public interface IMediaService {
    MediaResponse saveMedia(MediaRequest requestDTO);
}
