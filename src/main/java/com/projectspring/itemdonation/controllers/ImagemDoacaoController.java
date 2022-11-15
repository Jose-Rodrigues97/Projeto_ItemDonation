package com.projectspring.itemdonation.controllers;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projectspring.itemdonation.models.ImagemDoacaoModel;
import com.projectspring.itemdonation.services.ImagemDoacaoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/imagem")
public class ImagemDoacaoController {
    final ImagemDoacaoService imagemDoacaoService;

    public ImagemDoacaoController(ImagemDoacaoService imagemDoacaoService) {
        this.imagemDoacaoService = imagemDoacaoService;
    }

    @GetMapping("/doacao/{doacaoId}")
    public ResponseEntity<List<ImagemDoacaoModel>> ObterDoacaoPorUsuario(
            @PathVariable(value = "doacaoId") UUID doacaoId) {
        List<ImagemDoacaoModel> imagemDoacaoModelList = imagemDoacaoService.obterDoacoesUsuarioAll(doacaoId);
        return ResponseEntity.status(HttpStatus.OK).body(imagemDoacaoModelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ImagemDoacaoModel>> ObterDoacaoPorUsuario(
            @PathVariable(value = "id") Integer id) {
        Optional<ImagemDoacaoModel> imagemDoacaoModel = imagemDoacaoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(imagemDoacaoModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarDoacaoEntity(@PathVariable(value = "id") Integer id) {
        Optional<ImagemDoacaoModel> imagemDoacaoModel = imagemDoacaoService.findById(id);
        if (!imagemDoacaoModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem não encontrada.");
        }
        imagemDoacaoService.delete(imagemDoacaoModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Imagem excluída com sucesso.");
    }
}
