package com.projectspring.itemdonation.services;
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
    public ImagemDoacaoModel salvar(ImagemDoacaoModel imagemDoacaoModel) {
        return imagemDoacaoRepository.save(imagemDoacaoModel);
    }
}
