package com.projectspring.itemdonation.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.projectspring.itemdonation.models.RequisicaoModel;
import com.projectspring.itemdonation.repositories.RequisicaoRepository;

@Service
public class RequisicaoService {
    final RequisicaoRepository requisicaoRepository;
    
    public RequisicaoService(RequisicaoRepository requisicaoRepository) {
        this.requisicaoRepository = requisicaoRepository;
    }
    @Transactional
    public RequisicaoModel save(RequisicaoModel requisicaoModel){
        return requisicaoRepository.save(requisicaoModel);
    }

    public Optional<RequisicaoModel> findById(UUID requisicaoId){
        return requisicaoRepository.findById(requisicaoId);
    }

    public List<RequisicaoModel> obterRequisicoesByDoacaoAll(UUID doacaoId){
        return requisicaoRepository.findByDoacaoId(doacaoId); 
    }

    @Transactional
    public void delete(RequisicaoModel requisicaoModel) {
        requisicaoRepository.delete(requisicaoModel);
    }
}
