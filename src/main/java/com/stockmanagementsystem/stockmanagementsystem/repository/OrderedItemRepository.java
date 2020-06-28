package com.stockmanagementsystem.stockmanagementsystem.repository;

import com.stockmanagementsystem.stockmanagementsystem.models.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Integer> {
}
