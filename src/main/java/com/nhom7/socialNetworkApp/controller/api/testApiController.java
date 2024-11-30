package com.nhom7.socialNetworkApp.controller.api;


import com.nhom7.socialNetworkApp.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class testApiController {
    @GetMapping("/api/test")
    ApiResponse<String> testApi() {
        return ApiResponse.<String>builder()
                .status(200)
                .data("Hello World")
                .build();
    }
}
