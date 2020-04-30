package com.stockmanagementsystem.stockmanagementsystem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    public String logout(Model model){
//        RedirectView redirectView=new RedirectView("/login",true);
//        redirectAttributes.addFlashAttribute("logoutmessage","you have successfully logged out!!!");
//
//        return redirectView;
        model.addAttribute("logoutmessage","user logged out successfully");
        return "redirect:/login";
    }
}
