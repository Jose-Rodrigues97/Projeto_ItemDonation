package com.projectspring.itemdonation.repositories;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projectspring.itemdonation.models.DoacaoModel;

@Repository
public interface DoacaoRepository extends JpaRepository<DoacaoModel, UUID> {
    Page<DoacaoModel> findByPessoaId(UUID pessoaId, Pageable pageable);
}
