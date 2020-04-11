package com.stockmanagementsystem.stockmanagementsystem.Controller;

import com.stockmanagementsystem.stockmanagementsystem.models.Role;
import com.stockmanagementsystem.stockmanagementsystem.models.User;
import com.stockmanagementsystem.stockmanagementsystem.repository.RoleRepository;
import com.stockmanagementsystem.stockmanagementsystem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/regg")
    public String regtrial() {
        return "avc";
    }

//    @PostMapping("/saveuser")
//    public String saveUser(@RequestParam("image") MultipartFile multipartFile,
//                           @RequestParam("fname") String fname,
//                           @RequestParam("mname") String mname,
//                           @RequestParam("lname") String lname,
//                           @RequestParam("email") String email,
//                           @RequestParam("phone") String phone,
//                           @RequestParam("password") String password,
//                           @RequestParam("roles") List role) {
//
//        System.out.println("roles = " + role);
//
//        User user = new User();
//        user.setFname(fname);
//        user.setMname(mname);
//        user.setLname(lname);
//        user.setEmail(email);
//        user.setPassword(password);
//        user.setPhone(phone);
//        //user.setRoles((int)role.charAt(0));
//        user.setFilename(multipartFile.getOriginalFilename());
//        try {
//            user.setDataimage(multipartFile.getBytes());
//        } catch (IOException e) {
//
//        }
//        userRepository.save(user);
//
//        return "login";
//    }

    //    @Gesdfkakdfsadflhadfute(role);
//        return "register";add
//    }
    @GetMapping("/register")
    public String getAddUserForm(Model model) {
        List<User> allUsers = userRepository.findAll();
        System.out.println(allUsers);
        model.addAttribute("users", allUsers);
        List<Role> allRoles = roleRepository.findAll();
        System.out.println(allRoles);
        model.addAttribute("roles", allRoles);
        model.addAttribute("user", new User());
        return "register";
    }
    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model,@RequestParam("image") MultipartFile multipartFile) {

        User userExists = userRepository.findByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("errormsg", "field invalid");
            return "redirect:register";
        }

        System.out.println(user);
        user.setFilename(multipartFile.getOriginalFilename());
        try {
            user.setDataimage(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        userRepository.save(user);
        model.addAttribute("successMessage", "User has been registered successfully");
        return "login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }


}
