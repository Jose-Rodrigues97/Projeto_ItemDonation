package com.projectspring.itemdonation.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectspring.itemdonation.models.CategoriaModel;
import com.projectspring.itemdonation.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaModel> obterCategoriaAll() {
        return categoriaRepository.findAll();
    }

}
