package com.stockmanagementsystem.stockmanagementsystem.repository;

import com.stockmanagementsystem.stockmanagementsystem.models.DocumentNumbering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentNumberingRepository extends JpaRepository<DocumentNumbering, Integer> {
}
