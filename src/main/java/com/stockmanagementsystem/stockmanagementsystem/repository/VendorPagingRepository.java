package com.stockmanagementsystem.stockmanagementsystem.repository;

import com.stockmanagementsystem.stockmanagementsystem.models.Vendor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorPagingRepository extends PagingAndSortingRepository<Vendor,Integer> {

}
