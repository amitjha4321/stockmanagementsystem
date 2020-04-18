package com.stockmanagementsystem.stockmanagementsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

  @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String userDashboard(){
        return "/admin/dashboard";
    }
}
