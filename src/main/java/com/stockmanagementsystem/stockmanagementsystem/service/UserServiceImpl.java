package com.stockmanagementsystem.stockmanagementsystem.service;

import com.stockmanagementsystem.stockmanagementsystem.models.User;
import com.stockmanagementsystem.stockmanagementsystem.models.UserDetails;
import com.stockmanagementsystem.stockmanagementsystem.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


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
            userDetails.setId(user.getId());
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

    @Override
    public User findById(int id) {
      return userRepository.findById(id);
    }

    @Override
    public void updateUser(UserDetails  userDetails) throws Exception {
        User user=userRepository.findById(userDetails.getId());
        if (user==null){
            throw new Exception("User " + userDetails.getEmail() +"not found");
        }
        //BeanUtils.copyProperties(userDetails,user);

        user.setFname(userDetails.getFname());
        user.setLname(userDetails.getLname());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        //user.setRoles(userDetails.getRoles());


        //user.setPassword(user.getPassword());
        //user.setRoles(user.getRoles());
        //user.setFilename(user.getFilename());
        //user.setDataimage(user.getDataimage());
        userRepository.save(user);
        //System.out.println("++++++++++++++++++++++++++"+user);
    }

}
