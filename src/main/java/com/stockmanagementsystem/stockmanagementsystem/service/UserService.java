package com.stockmanagementsystem.stockmanagementsystem.service;

import com.stockmanagementsystem.stockmanagementsystem.models.UserDetails;

import java.util.List;

public interface UserService {
    List<UserDetails> findAllUsers();
}
