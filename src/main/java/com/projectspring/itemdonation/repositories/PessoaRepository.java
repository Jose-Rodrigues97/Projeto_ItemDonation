package com.projectspring.itemdonation.repositories;
import com.projectspring.itemdonation.models.PessoaModel;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, UUID>{
    public boolean existsByEmail(String email); 
    public boolean existsByTelefone(String telefone);
    public Optional<PessoaModel> findByEmailAndSenha(String email, String senha);
    
}