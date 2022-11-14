package com.projectspring.itemdonation.services;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
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

    public List<DoacaoModel> obterDoacoesAll() {
        return doacaoRepository.findAll();
    }

    public Optional<DoacaoModel> findById(UUID doacaoId) {
        return doacaoRepository.findById(doacaoId);
    }

    public List<DoacaoModel> obterDoacoesUsuarioAll(UUID pessoaId){
        return doacaoRepository.findByPessoaId(pessoaId);
    }

    @Transactional
    public void delete(DoacaoModel doacaoModel) {
        doacaoRepository.delete(doacaoModel);
    }
}
