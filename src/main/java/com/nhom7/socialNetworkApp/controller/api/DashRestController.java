package com.nhom7.socialNetworkApp.controller.api;


import com.nhom7.socialNetworkApp.services.IPostService;
import com.nhom7.socialNetworkApp.services.IReportSerice;
import com.nhom7.socialNetworkApp.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mod/dash")
@Slf4j
public class DashRestController {
    private final IUserService userService;
    private final IPostService postService;
    private final IReportSerice reportSerice;

    @GetMapping("/count")
    public Map<String, Long> countUsers() {
        long totalUsers = userService.count();
        log.info("Total users: {}", totalUsers);
        long totalPosts = postService.countPosts();
        log.info("Total Posts : {}", totalPosts);
        long totalReports = reportSerice.countReports();
        log.info("Total Reports : {}", totalReports);
        // Đóng gói kết quả dưới dạng JSON
        Map<String, Long> response = new HashMap<>();
        response.put("totalUsers", totalUsers);
        response.put("totalPosts", totalPosts);
        response.put("totalReports", totalReports);
        return response;
    }



}
