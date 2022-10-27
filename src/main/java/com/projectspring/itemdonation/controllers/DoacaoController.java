package com.projectspring.itemdonation.controllers;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
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
import com.projectspring.itemdonation.models.DoacaoModel;
import com.projectspring.itemdonation.dtos.DoacaoDto;
import com.projectspring.itemdonation.services.DoacaoService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/doacao")
public class DoacaoController {

    final DoacaoService doacaoService;

    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    @PostMapping
    public ResponseEntity<Object> SalvarDoacao(@RequestBody @Valid DoacaoDto doacaoDto) {
        var doacaoModel = new DoacaoModel();
        BeanUtils.copyProperties(doacaoDto, doacaoModel);
        doacaoModel.setDtCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(doacaoService.save(doacaoModel));
    }

    @GetMapping
    public ResponseEntity<List<DoacaoModel>> ObterDoacoes(){
        return ResponseEntity.status(HttpStatus.OK).body(doacaoService.obterIntencoesAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> ObterDoacao(@PathVariable(value = "id") UUID id){
        Optional <DoacaoModel> intencaoModelOptional = doacaoService.findById(id);
        if(!intencaoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doação não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(intencaoModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> AtualizarDoacao(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid DoacaoDto doacaoDto) {
        Optional<DoacaoModel> doacaoModelOptional = doacaoService.findById(id);
        if (!doacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doação não encontrado.");
        }
        var doacaoModel = doacaoModelOptional.get();
        doacaoModel.setTitulo(doacaoDto.getTitulo());
        doacaoModel.setDescricao(doacaoDto.getDescricao());
        doacaoModel.setRetirar(doacaoDto.isRetirar());
        doacaoModel.setEnderecoId(doacaoDto.getEnderecoId());
        doacaoModel.setCategoriaId(doacaoDto.getCategoriaId());
        doacaoModel.setItemId(doacaoDto.getItemId());
        
        return ResponseEntity.status(HttpStatus.OK).body(doacaoService.save(doacaoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarDoacaoEntity(@PathVariable(value = "id") UUID id){
        Optional<DoacaoModel> intencaoModelOptional = doacaoService.findById(id);
        if (!intencaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doação não encontrada.");
        }
        doacaoService.delete(intencaoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Doação excluída com sucesso.");
    }
}
