package com.stockmanagementsystem.stockmanagementsystem.repository;

import com.stockmanagementsystem.stockmanagementsystem.models.DocumentNumberingforSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentNumberingforSalesRepository extends JpaRepository<DocumentNumberingforSales,Integer> {

}
