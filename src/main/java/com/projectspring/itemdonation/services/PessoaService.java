package com.projectspring.itemdonation.services;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.projectspring.itemdonation.models.PessoaModel;
import com.projectspring.itemdonation.repositories.PessoaRepository;

@Service
public class PessoaService {

    final PessoaRepository pessoaRepository;
    
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public PessoaModel save(PessoaModel pessoaModel){
        return pessoaRepository.save(pessoaModel);
    }
    public Optional<PessoaModel> findById(UUID id){
        return pessoaRepository.findById(id);
    }
    public boolean existeTelefone(String telefone){
        return pessoaRepository.existsByTelefone(telefone);
    }
    public boolean existeEmail(String email){
        return pessoaRepository.existsByEmail(email);
    }
}