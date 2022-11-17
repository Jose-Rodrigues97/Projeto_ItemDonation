package com.projectspring.itemdonation.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
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
import com.projectspring.itemdonation.models.DoacaoModel;
import com.projectspring.itemdonation.models.RequisicaoModel;
import com.projectspring.itemdonation.models.StatusModel;
import com.projectspring.itemdonation.services.DoacaoService;
import com.projectspring.itemdonation.services.RequisicaoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/requisicao")
public class RequisicaoController {
    final RequisicaoService requisicaoService;
    final DoacaoService doacaoService;

    public RequisicaoController(RequisicaoService requisicaoService, DoacaoService doacaoService) {
        this.requisicaoService = requisicaoService;
        this.doacaoService = doacaoService;
    }

    @PostMapping
    public ResponseEntity<Object> SalvarRequisicao(@RequestBody @Valid RequisicaoDto requisicaoDto) {
        var requisicao = new RequisicaoModel();
        requisicao.setNomeRequisitante(requisicaoDto.getNomeRequisitante());
        ;
        requisicao.setEmailRequisitante(requisicaoDto.getEmailRequisitante());
        requisicao.setTelefoneRequisitante(requisicaoDto.getTelefoneRequisitante());
        requisicao.setMensagem(requisicaoDto.getMensagem());
        DoacaoModel doacao = new DoacaoModel();
        doacao.setId(requisicaoDto.getDoacaoId());
        requisicao.setDoacao(doacao);
        StatusModel status = new StatusModel();
        status.setStatusId(requisicaoDto.getStatus());
        requisicao.setStatus(status);
        requisicao.setDtCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(requisicaoService.save(requisicao));
    }

    @GetMapping("/{requisicaoId}")
    public ResponseEntity<Object> ObterPessoa(@PathVariable(value = "requisicaoId") UUID requisicaoId) {
        Optional<RequisicaoModel> requisicaoModelOptional = requisicaoService.findById(requisicaoId);
        if (!requisicaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requisição não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(requisicaoModelOptional.get());
    }

    @GetMapping("/doacao/{doacaoId}")
    public ResponseEntity<List<RequisicaoModel>> ObterRequisicoesPorDoacao(
            @PathVariable(value = "doacaoId") UUID doacaoId) {
        List<RequisicaoModel> requisicaoModelList = requisicaoService.obterRequisicoesByDoacaoAll(doacaoId);
        return ResponseEntity.status(HttpStatus.OK).body(requisicaoModelList);
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<RequisicaoModel>> ObterRequisicoesPorUsuario(
            @PathVariable(value = "pessoaId") UUID pessoaId) {
        List<DoacaoModel> doacaoModelList = doacaoService.obterDoacoesUsuarioAll(pessoaId);
        ArrayList<RequisicaoModel> requisicaoModelList = new ArrayList<RequisicaoModel>();
        for (DoacaoModel doacao : doacaoModelList) {
            requisicaoModelList.addAll(requisicaoService.obterRequisicoesByDoacaoAll(doacao.getId()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(requisicaoModelList);
    }
}
