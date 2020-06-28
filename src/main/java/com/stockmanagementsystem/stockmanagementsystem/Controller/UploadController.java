package com.stockmanagementsystem.stockmanagementsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/admin")
public class UploadController {

    @GetMapping("/upload")
    public String getUploadForm(){
        return "/admin/formupload";
    }


}
