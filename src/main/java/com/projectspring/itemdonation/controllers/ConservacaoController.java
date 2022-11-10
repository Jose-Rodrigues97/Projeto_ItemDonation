package com.projectspring.itemdonation.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectspring.itemdonation.models.ConservacaoModel;
import com.projectspring.itemdonation.services.ConservacaoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/conservacao")
public class ConservacaoController {
    final ConservacaoService conservacaoService;

    public ConservacaoController(ConservacaoService conservacaoService) {
        this.conservacaoService = conservacaoService;
    }

    @GetMapping
    public ResponseEntity<List<ConservacaoModel>> ObterConservacoes(){
        return ResponseEntity.status(HttpStatus.OK).body(conservacaoService.obterConservacaoAll());
    }
}
