package com.stockmanagementsystem.stockmanagementsystem.service;

import com.stockmanagementsystem.stockmanagementsystem.models.Role;
import com.stockmanagementsystem.stockmanagementsystem.models.User;
import com.stockmanagementsystem.stockmanagementsystem.models.UserDetails;
import com.stockmanagementsystem.stockmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDetails> findAllUsers() {
       List<User> userList=userRepository.findAll();
       List<UserDetails> userDetailsList=new ArrayList<>();
        for (User user:userList) {
            UserDetails userDetails=new UserDetails();
            userDetails.setFname(user.getFname());
            userDetails.setLname(user.getLname());
            userDetails.setEmail(user.getEmail());
            userDetails.setPhone(user.getPhone());
            userDetails.setRoles(user.getRoles());
            //userDetails.setRoles((Role) user.getRoles());
            if (user.getDataimage()!=null){
                userDetails.setBase64EncodedImage(Base64.getEncoder().encodeToString(user.getDataimage()));
            }
                userDetailsList.add(userDetails);
        }

        return userDetailsList;
    }
}
