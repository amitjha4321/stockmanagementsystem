package com.stockmanagementsystem.stockmanagementsystem.service;

import com.stockmanagementsystem.stockmanagementsystem.models.Purchase;
import com.stockmanagementsystem.stockmanagementsystem.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadForPurchase {

    @Autowired
    private PurchaseRepository purchaseRepository;

//    public Purchase saveFile(MultipartFile file){
//        String fileName = file.getOriginalFilename();
//        try {
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
