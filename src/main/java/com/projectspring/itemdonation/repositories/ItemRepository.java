package com.projectspring.itemdonation.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectspring.itemdonation.models.ItemModel;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, UUID>{
    
}
