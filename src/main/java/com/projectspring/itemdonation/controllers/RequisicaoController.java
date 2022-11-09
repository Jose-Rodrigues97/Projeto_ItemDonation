package com.projectspring.itemdonation.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectspring.itemdonation.dtos.RequisicaoDto;
import com.projectspring.itemdonation.models.RequisicaoModel;
import com.projectspring.itemdonation.services.RequisicaoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/requisicao")
public class RequisicaoController {
    final RequisicaoService requisicaoService;

    public RequisicaoController(RequisicaoService requisicaoService) {
        this.requisicaoService = requisicaoService;
    }

    @PostMapping
    public ResponseEntity<Object> SalvarRequisicao(@RequestBody @Valid RequisicaoDto requisicaoDto) {
        var requisicaoModel = new RequisicaoModel();
        BeanUtils.copyProperties(requisicaoDto, requisicaoModel);
        requisicaoModel.setDtCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(requisicaoService.save(requisicaoModel));
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<Object> ObterPessoa(@PathVariable(value = "pessoaId") UUID id) {
        Optional<RequisicaoModel> requisicaoModelOptional = requisicaoService.findById(id);
        if (!requisicaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requisição não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(requisicaoModelOptional.get());
    }
}
