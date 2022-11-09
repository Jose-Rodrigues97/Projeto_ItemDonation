package com.projectspring.itemdonation.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectspring.itemdonation.models.ConservacaoModel;

@Repository
public interface ConservacaoRepository extends JpaRepository<ConservacaoModel, UUID>{

}
