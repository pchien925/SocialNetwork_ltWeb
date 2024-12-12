package com.nhom7.socialNetworkApp.controller;


import com.nhom7.socialNetworkApp.services.IReportSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class ReportAdminController {

    private final IReportSerice reportSerice;
    private final RestTemplate restTemplate;

    // Constructor injection
    @Autowired
    public ReportAdminController(IReportSerice reportSerice, RestTemplate restTemplate) {
        this.reportSerice = reportSerice;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/admin/report")
    public String adminReport() {

        return "mod/report";  // Corresponds to a Thymeleaf template located in src/main/resources/templates/web/report.html
    }
    @GetMapping("/mod/report")
    public String modReport() {

        return "mod/report";  // Corresponds to a Thymeleaf template located in src/main/resources/templates/web/report.html
    }







    @GetMapping("/mod/post-report/{reportId}")
    public String viewPostReport(@PathVariable("reportId") String reportId, Model model) {
        // Lấy dữ liệu bài viết theo postId
        // Ví dụ:
        // Post post = postService.getPostById(postId);
        model.addAttribute("reportId", reportId);  // Thêm postId vào model
        return "mod/report-post";  // Trả về view (JSP hoặc Thymeleaf)

    }
    @GetMapping("/admin/post-report/{reportId}")
    public String viewadminPostReport(@PathVariable("reportId") String reportId, Model model) {
        // Lấy dữ liệu bài viết theo postId
        // Ví dụ:
        // Post post = postService.getPostById(postId);
        model.addAttribute("reportId", reportId);  // Thêm postId vào model
        return "mod/report-post";  // Trả về view (JSP hoặc Thymeleaf)

    }
}
