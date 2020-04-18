package com.stockmanagementsystem.stockmanagementsystem.service;

import com.stockmanagementsystem.stockmanagementsystem.models.User;
import com.stockmanagementsystem.stockmanagementsystem.models.UserPrincipal;
import com.stockmanagementsystem.stockmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService  implements UserDetailsService{


    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(email);
        if (user==null){
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }
}
