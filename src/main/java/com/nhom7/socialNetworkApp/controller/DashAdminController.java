package com.nhom7.socialNetworkApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashAdminController {

    @GetMapping("/admin/dash")

    public String adminDash()
    {
        return "mod/dash";
    }
    @GetMapping("/mod/dash")
    public String modDash()
    {
        return "mod/dash";
    }
}
