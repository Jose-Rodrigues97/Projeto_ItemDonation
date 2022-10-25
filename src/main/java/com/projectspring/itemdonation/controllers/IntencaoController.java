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
import com.projectspring.itemdonation.models.IntencaoModel;
import com.projectspring.itemdonation.dtos.IntencaoDto;
import com.projectspring.itemdonation.services.IntencaoService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/doacao")
public class IntencaoController {

    final IntencaoService intencaoService;

    public IntencaoController(IntencaoService intencaoService) {
        this.intencaoService = intencaoService;
    }

    @PostMapping
    public ResponseEntity<Object> SalvarDoacao(@RequestBody @Valid IntencaoDto intencaoDto) {
        var intencaoModel = new IntencaoModel();
        BeanUtils.copyProperties(intencaoDto, intencaoModel);
        intencaoModel.setDtCriacao(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(intencaoService.save(intencaoModel));
    }

    @GetMapping
    public ResponseEntity<List<IntencaoModel>> ObterDoacoes(){
        return ResponseEntity.status(HttpStatus.OK).body(intencaoService.obterIntencoesAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> ObterDoacao(@PathVariable(value = "id") UUID id){
        Optional <IntencaoModel> intencaoModelOptional = intencaoService.findById(id);
        if(!intencaoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doação não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(intencaoModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> AtualizarDoacao(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid IntencaoDto intencaoDto) {
        Optional<IntencaoModel> intencaoModelOptional = intencaoService.findById(id);
        if (!intencaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doação não encontrado.");
        }
        var intencaoModel = intencaoModelOptional.get();
        intencaoModel.setTitulo(intencaoDto.getTitulo());
        intencaoModel.setDescricao(intencaoDto.getDescricao());
        intencaoModel.setIsRetirar(intencaoDto.getIsRetirar());
        intencaoModel.setEnderecoId(intencaoDto.getEnderecoId());
        intencaoModel.setCategoriaId(intencaoDto.getCategoriaId());
        intencaoModel.setItemId(intencaoDto.getItemId());
        return ResponseEntity.status(HttpStatus.OK).body(intencaoService.save(intencaoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarDoacaoEntity(@PathVariable(value = "id") UUID id){
        Optional<IntencaoModel> intencaoModelOptional = intencaoService.findById(id);
        if (!intencaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doação não encontrada.");
        }
        intencaoService.delete(intencaoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Doação excluída com sucesso.");
    }
}
