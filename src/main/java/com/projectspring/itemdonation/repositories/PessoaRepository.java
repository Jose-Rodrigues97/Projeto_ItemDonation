package com.projectspring.itemdonation.repositories;
import com.projectspring.itemdonation.models.PessoaModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, UUID>{
    boolean existsByEmail(String email); 
    boolean existsByTelefone(String telefone);
}