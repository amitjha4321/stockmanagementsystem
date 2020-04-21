package com.stockmanagementsystem.stockmanagementsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

//  @RequestMapping(value = "", method = RequestMethod.GET)
//    public String userDashboard(){
//        return "dashboard";
//    }

    @GetMapping(value = {"","/admin","/user"})
    public String loginsend(){
        return "dashboard";
    }
}
