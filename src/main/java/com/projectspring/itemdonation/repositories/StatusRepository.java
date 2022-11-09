package com.projectspring.itemdonation.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectspring.itemdonation.models.StatusModel;

@Repository
public interface StatusRepository extends JpaRepository<StatusModel, UUID>{

}
