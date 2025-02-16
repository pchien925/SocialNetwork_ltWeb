package com.nhom7.socialNetworkApp.mapper;

import com.nhom7.socialNetworkApp.dto.request.PostCreationRequest;
import com.nhom7.socialNetworkApp.dto.response.PostResponse;
import com.nhom7.socialNetworkApp.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toPost(PostCreationRequest request);
    PostResponse toPostResponse(Post post);

    void update(@MappingTarget Post post, PostCreationRequest request);
}
