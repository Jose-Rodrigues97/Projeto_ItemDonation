package com.projectspring.itemdonation.dtos;

import java.util.List;

import com.projectspring.itemdonation.models.DoacaoModel;
import com.projectspring.itemdonation.models.ImagemDoacaoModel;
import com.projectspring.itemdonation.models.RequisicaoModel;

import lombok.Data;

@Data
public class DoacaoReqImagDto {
    private DoacaoModel doacao;
    private List<ImagemDoacaoModel> imagens;
    private List<RequisicaoModel> requisicoes;
    private Integer numRequisicoes;
}
