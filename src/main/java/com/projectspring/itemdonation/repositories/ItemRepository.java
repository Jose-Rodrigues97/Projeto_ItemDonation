package com.projectspring.itemdonation.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectspring.itemdonation.models.ItemModel;

public interface ItemRepository extends JpaRepository<ItemModel, UUID>{
    
}
