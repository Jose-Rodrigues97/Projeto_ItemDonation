package com.projectspring.itemdonation.controllers;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projectspring.itemdonation.models.ConservacaoModel;
import com.projectspring.itemdonation.models.DoacaoModel;
import com.projectspring.itemdonation.models.ImagemDoacaoModel;
import com.projectspring.itemdonation.models.ItemModel;
import com.projectspring.itemdonation.models.PessoaModel;
import com.projectspring.itemdonation.models.StatusModel;
import com.projectspring.itemdonation.dtos.DoacaoDto;
import com.projectspring.itemdonation.services.DoacaoService;
import com.projectspring.itemdonation.services.ImagemDoacaoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/doacao")
public class DoacaoController {

    final DoacaoService doacaoService;
    final ImagemDoacaoService imagemDoacaoService;

    public DoacaoController(DoacaoService doacaoService, ImagemDoacaoService imagemDoacaoService) {
        this.doacaoService = doacaoService;
        this.imagemDoacaoService = imagemDoacaoService;
    }

    @PostMapping
    public ResponseEntity<Object> SalvarDoacao(@RequestBody @Valid DoacaoDto doacaoDto) {
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
        ImagemDoacaoModel imagemDoacao = new ImagemDoacaoModel();
        if (!(doacaoDto.getImagens() == null)) {
            for (String imagem : doacaoDto.getImagens()) {
                imagemDoacao.setBinario(imagem);
                imagemDoacao.setDoacao(doacaoModel);
                imagemDoacaoService.salvar(imagemDoacao);
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(doacaoService.salvar(doacaoModel));
    }

    @GetMapping
    public ResponseEntity<List<DoacaoModel>> ObterDoacoes() {
        return ResponseEntity.status(HttpStatus.OK).body(doacaoService.obterDoacoesAll());
    }

    @GetMapping("/{doacaoId}")
    public ResponseEntity<Object> ObterDoacao(@PathVariable(value = "doacaoId") UUID doacaoId) {
        Optional<DoacaoModel> doacaoModelOptional = doacaoService.findById(doacaoId);
        if (!doacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doação não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(doacaoModelOptional.get());
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<DoacaoModel>> ObterDoacaoPorUsuario(@PathVariable(value = "pessoaId") UUID pessoaId) {
        List<DoacaoModel> doacaoModelList = doacaoService.obterDoacoesUsuarioAll(pessoaId);
        return ResponseEntity.status(HttpStatus.OK).body(doacaoModelList);
    }

    @PutMapping("/{doacaoId}")
    public ResponseEntity<Object> AtualizarDoacao(@PathVariable(value = "doacaoId") UUID doacaoId,
            @RequestBody @Valid DoacaoDto doacaoDto) {
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
