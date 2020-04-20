package com.stockmanagementsystem.stockmanagementsystem.service;

import com.stockmanagementsystem.stockmanagementsystem.models.User;
import com.stockmanagementsystem.stockmanagementsystem.models.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDetails> findAllUsers();

    User findById(int id);

    void updateUser(UserDetails userDetails) throws Exception;
}
