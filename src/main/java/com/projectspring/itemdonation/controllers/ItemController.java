package com.projectspring.itemdonation.controllers;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projectspring.itemdonation.models.ItemModel;
import com.projectspring.itemdonation.services.ItemService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/itens")
public class ItemController {
    final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemModel>> ObterItens(){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.obterItemAll());
    }
}
