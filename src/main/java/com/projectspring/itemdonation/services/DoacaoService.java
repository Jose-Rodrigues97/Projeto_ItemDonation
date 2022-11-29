package com.projectspring.itemdonation.services;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.projectspring.itemdonation.models.DoacaoModel;
import com.projectspring.itemdonation.repositories.DoacaoRepository;

@Service
public class DoacaoService {
    final DoacaoRepository doacaoRepository;

    public DoacaoService(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }

    @Transactional
    public DoacaoModel salvar(DoacaoModel doacaoModel) {
        return doacaoRepository.save(doacaoModel);
    }

    public Page<DoacaoModel> obterDoacoesAll(Pageable pageable) {
        return doacaoRepository.findAll(pageable);
    }

    public Optional<DoacaoModel> findById(UUID doacaoId) {
        return doacaoRepository.findById(doacaoId);
    }

    public Page<DoacaoModel> obterDoacoesUsuarioAll(UUID pessoaId, Pageable pageable){
        return doacaoRepository.findByPessoaId(pessoaId, pageable);
    }

    @Transactional
    public void delete(DoacaoModel doacaoModel) {
        doacaoRepository.delete(doacaoModel);
    }
}
