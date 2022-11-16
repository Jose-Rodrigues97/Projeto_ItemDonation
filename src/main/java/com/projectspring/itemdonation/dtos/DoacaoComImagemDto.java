package com.projectspring.itemdonation.dtos;
import java.util.List;
import com.projectspring.itemdonation.models.DoacaoModel;
import com.projectspring.itemdonation.models.ImagemDoacaoModel;

import lombok.Data;

@Data
public class DoacaoComImagemDto {
    private DoacaoModel doacao;
    private List<ImagemDoacaoModel> imagens;
}
