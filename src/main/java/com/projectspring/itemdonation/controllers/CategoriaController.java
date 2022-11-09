package com.projectspring.itemdonation.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectspring.itemdonation.models.CategoriaModel;
import com.projectspring.itemdonation.services.CategoriaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/categorias")
public class CategoriaController {
    final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaModel>> ObterCategorias(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.obterCategoriaAll());
    }
}
