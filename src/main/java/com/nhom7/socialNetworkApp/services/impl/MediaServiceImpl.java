package com.nhom7.socialNetworkApp.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhom7.socialNetworkApp.dto.request.MediaRequest;
import com.nhom7.socialNetworkApp.dto.response.MediaResponse;
import com.nhom7.socialNetworkApp.entity.Media;
import com.nhom7.socialNetworkApp.mapper.MediaMapper;
import com.nhom7.socialNetworkApp.repository.MediaRepository;
import com.nhom7.socialNetworkApp.services.IMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements IMediaService {
    private final Cloudinary cloudinary;
    private final MediaMapper mediaMapper;
    private final MediaRepository mediaRepository;

    @Override
    public MediaResponse saveMedia(MediaRequest request) {
        Media media = this.uploadFile(request);
        return mediaMapper.toMediaResponse(mediaRepository.save(media));
    }

    private Media uploadFile(MediaRequest request) {
        try {
            Map<?, ?> result = cloudinary.uploader().upload(request.getFile().getBytes(), ObjectUtils.emptyMap());

            return Media.builder()
                    .mediaKey((String) result.get("public_id"))
                    .caption(request.getCaption())
                    .url(result.get("url").toString())
                    .type(result.get("resource_type").toString())
                    .build();

        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
