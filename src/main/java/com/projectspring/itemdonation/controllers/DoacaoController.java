package com.projectspring.itemdonation.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.projectspring.itemdonation.models.ConservacaoModel;
import com.projectspring.itemdonation.models.DoacaoModel;
import com.projectspring.itemdonation.models.ImagemDoacaoModel;
import com.projectspring.itemdonation.models.ItemModel;
import com.projectspring.itemdonation.models.PessoaModel;
import com.projectspring.itemdonation.models.StatusModel;
import com.projectspring.itemdonation.dtos.DoacaoComImagemDto;
import com.projectspring.itemdonation.dtos.DoacaoDto;
import com.projectspring.itemdonation.dtos.DoacaoReqImagDto;
import com.projectspring.itemdonation.services.DoacaoService;
import com.projectspring.itemdonation.services.ImagemDoacaoService;
import com.projectspring.itemdonation.services.RequisicaoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/doacao")
public class DoacaoController {

    final DoacaoService doacaoService;
    final ImagemDoacaoService imagemDoacaoService;
    final RequisicaoService requisicaoService;

    public DoacaoController(DoacaoService doacaoService, ImagemDoacaoService imagemDoacaoService,
            RequisicaoService requisicaoService) {
        this.doacaoService = doacaoService;
        this.imagemDoacaoService = imagemDoacaoService;
        this.requisicaoService = requisicaoService;
    }

    @PostMapping
    public ResponseEntity<Object> SalvarDoacao(@Valid DoacaoDto doacaoDto,
            @RequestParam("files") ArrayList<MultipartFile> files) {
        var doacaoModel = new DoacaoModel();
        doacaoModel.setBairro(doacaoDto.getBairro());
        doacaoModel.setCidade(doacaoDto.getCidade());
        doacaoModel.setDescricao(doacaoDto.getDescricao());
        doacaoModel.setEndereco(doacaoDto.getEndereco());
        doacaoModel.setEstado(doacaoDto.getEstado());
        doacaoModel.setRetirar(doacaoDto.isRetirar());
        doacaoModel.setTitulo(doacaoDto.getTitulo());
        doacaoModel.setDtCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        PessoaModel pessoa = new PessoaModel();
        pessoa.setId(doacaoDto.getPessoaId());
        doacaoModel.setPessoa(pessoa);
        ItemModel item = new ItemModel();
        item.setItemId(doacaoDto.getItemId());
        doacaoModel.setItem(item);
        ConservacaoModel conservacao = new ConservacaoModel();
        conservacao.setConservacaoId(doacaoDto.getConservacaoId());
        doacaoModel.setConservacao(conservacao);
        StatusModel status = new StatusModel();
        status.setStatusId(doacaoDto.getStatus());
        doacaoModel.setStatus(status);
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED)
                .body(doacaoService.salvar(doacaoModel));
        try {
            if (!(files.isEmpty())) {
                for (MultipartFile file : files) {
                    ImagemDoacaoModel imagemDoacao = new ImagemDoacaoModel();
                    imagemDoacao.setBinario(file.getBytes());
                    imagemDoacao.setDoacao(doacaoModel);
                    imagemDoacaoService.salvar(imagemDoacao);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<Page<DoacaoComImagemDto>> ObterDoacoes(Pageable pageable) {
        var doacoesPage = doacaoService.obterDoacoesAll(pageable);
        ArrayList<DoacaoComImagemDto> doacaoComImagemDtoList = new ArrayList<DoacaoComImagemDto>();
        for (DoacaoModel doacao : doacoesPage) {
            DoacaoComImagemDto doacaoComImagemDto = new DoacaoComImagemDto();
            doacaoComImagemDto.setDoacao(doacao);
            doacaoComImagemDto.setImagens(imagemDoacaoService.obterImagensDoacoesAll(doacao.getId()));
            doacaoComImagemDtoList.add(doacaoComImagemDto);
        }
        Page<DoacaoComImagemDto> doacaoComImagemDtoPage = new PageImpl<DoacaoComImagemDto>(doacaoComImagemDtoList, pageable, doacoesPage.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(doacaoComImagemDtoPage);
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<Page<DoacaoReqImagDto>> ObterDoacaoPorUsuario(
            @PathVariable(value = "pessoaId") UUID pessoaId, Pageable pageable) {
        Page<DoacaoModel> doacaoModelPage = doacaoService.obterDoacoesUsuarioAll(pessoaId, pageable);
        ArrayList<DoacaoReqImagDto> doacaoReqImagDtoList = new ArrayList<DoacaoReqImagDto>();
        for (DoacaoModel doacao : doacaoModelPage) {
            DoacaoReqImagDto doacaoReqImagDto = new DoacaoReqImagDto();
            doacaoReqImagDto.setDoacao(doacao);
            doacaoReqImagDto.setImagens(imagemDoacaoService.obterImagensDoacoesAll(doacao.getId()));
            doacaoReqImagDto.setRequisicoes(requisicaoService.obterRequisicoesByDoacaoAll(doacao.getId()));
            doacaoReqImagDto.setNumRequisicoes(doacaoReqImagDto.getRequisicoes().size());
            doacaoReqImagDtoList.add(doacaoReqImagDto);
        }
        Page<DoacaoReqImagDto> doacaoComImagemDtoPage = new PageImpl<DoacaoReqImagDto>(doacaoReqImagDtoList, pageable, doacaoModelPage.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(doacaoComImagemDtoPage);
    }

    @GetMapping("/{doacaoId}")
    public ResponseEntity<DoacaoComImagemDto> ObterDoacao(@PathVariable(value = "doacaoId") UUID doacaoId) {
        Optional<DoacaoModel> doacaoModelOptional = doacaoService.findById(doacaoId);
        DoacaoComImagemDto doacaoImage = new DoacaoComImagemDto();
        if (!doacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        var doacao = doacaoModelOptional.get();
        doacaoImage.setDoacao(doacao);
        doacaoImage.setImagens(imagemDoacaoService.obterImagensDoacoesAll(doacao.getId()));
        return ResponseEntity.status(HttpStatus.OK).body(doacaoImage);
    }

    @PutMapping("/{doacaoId}")
    public ResponseEntity<Object> AtualizarDoacao(@PathVariable(value = "doacaoId") UUID doacaoId,
            DoacaoDto doacaoDto, @RequestParam("file") ArrayList<MultipartFile> files) {
        Optional<DoacaoModel> doacaoModelOptional = doacaoService.findById(doacaoId);
        if (!doacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doação não encontrada.");
        }
        var doacaoModel = doacaoModelOptional.get();
        doacaoModel.setTitulo(doacaoDto.getTitulo());
        doacaoModel.setDescricao(doacaoDto.getDescricao());
        doacaoModel.setRetirar(doacaoDto.isRetirar());
        doacaoModel.setEndereco(doacaoDto.getEndereco());
        doacaoModel.setBairro(doacaoDto.getBairro());
        doacaoModel.setCidade(doacaoDto.getCidade());
        doacaoModel.setEstado(doacaoDto.getEstado());
        if (!(doacaoDto.getItemId() == null)) {
            ItemModel item = new ItemModel();
            item.setItemId(doacaoDto.getItemId());
            doacaoModel.setItem(item);
        }
        if (!(doacaoDto.getConservacaoId() == null)) {
            ConservacaoModel conservacao = new ConservacaoModel();
            conservacao.setConservacaoId(doacaoDto.getConservacaoId());
            doacaoModel.setConservacao(conservacao);
        }
        if (!(doacaoDto.getStatus() == null)) {
            StatusModel status = new StatusModel();
            status.setStatusId(doacaoDto.getStatus());
            doacaoModel.setStatus(status);
        }
        try {
            if (!(files.isEmpty())) {
                for (MultipartFile file : files) {
                    ImagemDoacaoModel imagemDoacao = new ImagemDoacaoModel();
                    imagemDoacao.setBinario(file.getBytes());
                    imagemDoacao.setDoacao(doacaoModel);
                    imagemDoacaoService.salvar(imagemDoacao);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(doacaoService.salvar(doacaoModel));
    }

    @DeleteMapping("/{doacaoId}")
    public ResponseEntity<Object> deletarDoacaoEntity(@PathVariable(value = "doacaoId") UUID doacaoId) {
        Optional<DoacaoModel> doacaoModelOptional = doacaoService.findById(doacaoId);
        if (!doacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doação não encontrada.");
        }
        doacaoService.delete(doacaoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Doação excluída com sucesso.");
    }
}