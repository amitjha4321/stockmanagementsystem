package com.stockmanagementsystem.stockmanagementsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccesDenied {


    @GetMapping("/admin/403")
    public String accesseniedpage(){
        return "/admin/403";
    }
}
