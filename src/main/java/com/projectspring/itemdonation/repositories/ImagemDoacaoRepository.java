package com.projectspring.itemdonation.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectspring.itemdonation.models.ImagemDoacaoModel;

@Repository
public interface ImagemDoacaoRepository extends JpaRepository<ImagemDoacaoModel, Integer>{
    List<ImagemDoacaoModel> findByDoacaoId(UUID doacaoId);
}
