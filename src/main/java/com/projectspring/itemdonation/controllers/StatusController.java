package com.projectspring.itemdonation.controllers;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projectspring.itemdonation.models.StatusModel;
import com.projectspring.itemdonation.services.StatusService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/status")
public class StatusController {
    final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<StatusModel>> ObterStatus(){
        return ResponseEntity.status(HttpStatus.OK).body(statusService.obterStatusAll());
    }
}
