package com.stockmanagementsystem.stockmanagementsystem.Controller;

import com.stockmanagementsystem.stockmanagementsystem.models.User;
import com.stockmanagementsystem.stockmanagementsystem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/saveuser")
    public String saveUser(@RequestParam("image") MultipartFile multipartFile,
                           @RequestParam("fname") String fname,
                           @RequestParam("mname") String mname,
                           @RequestParam("lname") String lname,
                           @RequestParam("email") String email,
                           @RequestParam("phone") String phone,
                           @RequestParam("password") String password){

        User user=new User();
            user.setFname(fname);
            user.setMname(mname);
            user.setLname(lname);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            user.setFilename(multipartFile.getOriginalFilename());
        try {
            user.setDataimage(multipartFile.getBytes());
        } catch (IOException e) {

        }
            userRepository.save(user);

        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }



}
