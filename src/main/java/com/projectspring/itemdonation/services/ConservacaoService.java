package com.projectspring.itemdonation.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectspring.itemdonation.models.ConservacaoModel;
import com.projectspring.itemdonation.repositories.ConservacaoRepository;

@Service
public class ConservacaoService {
    final ConservacaoRepository conservacaoRepository;

    public ConservacaoService(ConservacaoRepository conservacaoRepository) {
        this.conservacaoRepository = conservacaoRepository;
    }

    public List<ConservacaoModel> obterConservacaoAll() {
        return conservacaoRepository.findAll();
    }
}
