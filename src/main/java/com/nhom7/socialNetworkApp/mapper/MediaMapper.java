package com.nhom7.socialNetworkApp.mapper;

import com.nhom7.socialNetworkApp.dto.response.MediaResponse;
import com.nhom7.socialNetworkApp.entity.Media;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    MediaResponse toMediaResponse(Media media);
}
