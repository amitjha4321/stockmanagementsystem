package com.stockmanagementsystem.stockmanagementsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller


public class UserDashboardController {

    @GetMapping("/user/dashboard")
    public String userDashboard(){
        return "dashboard";
    }
}
