package com.projectspring.itemdonation.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.projectspring.itemdonation.models.IntencaoModel;
import com.projectspring.itemdonation.repositories.IntencaoRepository;

@Service
public class IntencaoService {
    final IntencaoRepository intencaoRepository;

    public IntencaoService(IntencaoRepository intencaoRepository) {
        this.intencaoRepository = intencaoRepository;
    }

    @Transactional
    public IntencaoModel save(IntencaoModel intencaoModel) {
        return intencaoRepository.save(intencaoModel);
    }

    public List<IntencaoModel> obterIntencoesAll() {
        return intencaoRepository.findAll();
    }

    public Optional<IntencaoModel> findById(UUID id) {
        return intencaoRepository.findById(id);
    }

    @Transactional
    public void delete(IntencaoModel intencaoModel) {
        intencaoRepository.delete(intencaoModel);
    }
}
