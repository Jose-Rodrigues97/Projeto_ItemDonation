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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projectspring.itemdonation.dtos.PessoaDto;
import com.projectspring.itemdonation.models.PessoaModel;
import com.projectspring.itemdonation.services.PessoaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pessoa")
public class PessoaController {

    final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Object> SalvarPessoa(@RequestBody PessoaDto pessoaDto) {
        if (pessoaService.existeEmail(pessoaDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já está cadastrado.");
        }
        if (pessoaService.existeTelefone(pessoaDto.getTelefone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Telefone já está cadastrado.");
        }
        var pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaDto, pessoaModel);
        pessoaModel.setDtCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaModel));
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<Object> ObterPessoa(@PathVariable(value = "pessoaId") UUID pessoaId) {
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(pessoaId);
        if (!pessoaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional.get());
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<Object> AtualizarPessoa(@PathVariable(value = "pessoaId") UUID pessoaId,
            @RequestBody @Valid PessoaModel pessoaModel) {
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(pessoaId);
        if (!pessoaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        PessoaModel pessoa = pessoaModelOptional.get();
        if(!(pessoaModel.getNome() == null || pessoaModel.getNome() == "")){
            pessoa.setNome(pessoaModel.getNome());
        }
        if(!(pessoaModel.getEmail() == null || pessoaModel.getEmail() == "")){
            pessoa.setEmail(pessoaModel.getEmail());
        }
        if(!(pessoaModel.getTelefone() == null || pessoaModel.getTelefone() == "")){
            pessoa.setTelefone(pessoaModel.getTelefone());
        }
        if(!(pessoaModel.getSenha() == null || pessoaModel.getSenha() == "")){
            pessoa.setSenha(pessoaModel.getSenha());
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoa));
    }

    @GetMapping("/{email}/{senha}")
    public ResponseEntity<Object> ObterPessoa(@PathVariable(value = "email") String email, @PathVariable(value = "senha") String senha) {
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findByLogin(email, senha);
        if (!pessoaModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login inválido.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional.get());
    }
}
