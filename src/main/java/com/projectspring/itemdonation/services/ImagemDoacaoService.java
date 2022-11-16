package com.projectspring.itemdonation.services;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.projectspring.itemdonation.models.ImagemDoacaoModel;
import com.projectspring.itemdonation.repositories.ImagemDoacaoRepository;

@Service
public class ImagemDoacaoService {
    final ImagemDoacaoRepository imagemDoacaoRepository;

    public ImagemDoacaoService(ImagemDoacaoRepository imagemDoacaoRepository) {
        this.imagemDoacaoRepository = imagemDoacaoRepository;
    }
    
    @Transactional
    public void salvar(ImagemDoacaoModel imagemDoacaoModel) {
        imagemDoacaoRepository.save(imagemDoacaoModel);
    }

    public Optional<ImagemDoacaoModel> findById(Integer id) {
        return imagemDoacaoRepository.findById(id);
    }

    public List<ImagemDoacaoModel> obterImagensDoacoesAll(UUID doacaoId){
        return imagemDoacaoRepository.findByDoacaoId(doacaoId);
    }

    @Transactional
    public void delete(ImagemDoacaoModel imagemDoacaoModel) {
        imagemDoacaoRepository.delete(imagemDoacaoModel);
    }
}
