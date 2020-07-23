package com.stockmanagementsystem.stockmanagementsystem.Controller;

import com.stockmanagementsystem.stockmanagementsystem.models.Role;
import com.stockmanagementsystem.stockmanagementsystem.models.User;
import com.stockmanagementsystem.stockmanagementsystem.models.UserDetails;
import com.stockmanagementsystem.stockmanagementsystem.repository.RoleRepository;
import com.stockmanagementsystem.stockmanagementsystem.repository.UserRepository;

import com.stockmanagementsystem.stockmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

//    @GetMapping("/regg")
//    public String regtrial() {
//        return "avc";
//    }

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAddUserForm(Model model) {
        List<User> allUsers = userRepository.findAll();
        System.out.println(allUsers);
        model.addAttribute("users", allUsers);
        List<Role> allRoles = roleRepository.findAll();
        System.out.println(allRoles);
        model.addAttribute("roles", allRoles);
        model.addAttribute("user", new User());
        return "admin/register";
    }
    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public //RedirectView
            String
    createNewUser(@Valid User user, BindingResult bindingResult, Model model, @RequestParam("image") MultipartFile multipartFile
                                        , RedirectAttributes redir) {

        User userExists = userRepository.findByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("errormsg", "field invalid");
            //return "redirect:/register";
        }

        //System.out.println(user);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setFilename(multipartFile.getOriginalFilename());
        try {
            user.setDataimage(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        userRepository.save(user);
        model.addAttribute("successMessage", "User has been registered successfully");
        //RedirectView redirectView=new RedirectView("/login",true);
        //RedirectView redirectView=new RedirectView("/register?success=true",true);
        //redir.addFlashAttribute("message","User Registered Successfully!!!");

        //return "/admin/login";

        //return redirectView;

        return "redirect:/register?register=true";
    }


//    @GetMapping(value = {"/login","/"})
//    public String showLogin() {
//        return "/admin/login";
//    }
//
//    @PostMapping("/login")
//    public String doLogin(@RequestParam("email") String username, @RequestParam("password") String password) {
//        User user = userRepository.findByEmail(username);
//        if (user.getPassword().equals(password)) {
//            return "admin/dashboard";
//        } else {
//            return "admin/login";
//        }
//    }


    @GetMapping("/userlist")
    public String displayUserList(Model model, Principal principal){

        // interceptor

        // principal.getName() -> logged in user's email id
        //System.out.println("principal = " + principal.getName());
        UserDetails userDetails = userService.findUserDetailByEmail(principal.getName());


        //userDetailsService.loadUserByUsername()
        //List<User> userList= userRepository.findAll();
        List<UserDetails> userDetailsList=userService.findAllUsers();
        System.out.println(userDetails.isEnabled());
        model.addAttribute("users",userDetailsList);
        model.addAttribute("logged_in_user_base64_pic", userDetails.getBase64EncodedImage());
        return "/admin/userlist";
    }

    @GetMapping("/users/findbyid")
    @ResponseBody
    public User findById(int id){
        return userService.findById(id);
    }

    @RequestMapping(value = "/users/update",method = {RequestMethod.PUT , RequestMethod.GET})
    public String update(UserDetails user) throws Exception {
            userService.updateUser(user);
        return "redirect:/userlist";
    }

    @RequestMapping(value = { "/delete/{email}" },method = {RequestMethod.DELETE , RequestMethod.GET})
    public String delete(@PathVariable String email) throws Exception {
       // userService.delete(email);
        userService.deleteUserByUserName(email);
        return "redirect:/userlist";
    }

    @RequestMapping(value ={"/user/enable/{id}"},method = {RequestMethod.PUT,RequestMethod.GET})
    public String enable(@PathVariable int id){
       User user= userRepository.findById(id);
       if(user.isEnabled())
           user.setEnabled(false);
       else
           user.setEnabled(true);
       userRepository.save(user);

        return "redirect:/userlist";
    }
//    @GetMapping(value = { "/delete/{userName}" })
//    public String deleteUser(@PathVariable String userName) throws Exception {
//        userService.deleteUserByUserName(userName);
//        return "redirect:/list";
//    }
}
