package com.projectspring.itemdonation.repositories;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectspring.itemdonation.models.IntencaoModel;

@Repository
public interface IntencaoRepository extends JpaRepository<IntencaoModel, UUID>{
    
}
