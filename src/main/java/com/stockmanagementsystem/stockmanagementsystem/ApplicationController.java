package com.stockmanagementsystem.stockmanagementsystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class ApplicationController {

    @GetMapping("/")
    public String goHome(){
        return "dashboard";
    }

    @GetMapping("/login")
    public String login(){
        return "/admin/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "/admin/login";
    }
}
