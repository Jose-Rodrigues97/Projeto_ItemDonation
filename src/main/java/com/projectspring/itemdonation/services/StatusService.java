package com.projectspring.itemdonation.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectspring.itemdonation.models.StatusModel;
import com.projectspring.itemdonation.repositories.StatusRepository;

@Service
public class StatusService {
    final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<StatusModel> obterStatusAll() {
        return statusRepository.findAll();
    }
}
