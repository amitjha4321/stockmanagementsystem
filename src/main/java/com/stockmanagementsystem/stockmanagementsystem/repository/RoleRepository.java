package com.stockmanagementsystem.stockmanagementsystem.repository;

import com.stockmanagementsystem.stockmanagementsystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
