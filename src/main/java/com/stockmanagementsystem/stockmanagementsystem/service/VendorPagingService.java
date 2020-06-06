package com.stockmanagementsystem.stockmanagementsystem.service;


import com.stockmanagementsystem.stockmanagementsystem.models.Vendor;
import com.stockmanagementsystem.stockmanagementsystem.repository.VendorPagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorPagingService {

    @Autowired
    private VendorPagingRepository vendorPagingRepository;

    public Page<Vendor> listAll(){
        Pageable pageable= PageRequest.of(0,2);
        return vendorPagingRepository.findAll(pageable);
    }


}
