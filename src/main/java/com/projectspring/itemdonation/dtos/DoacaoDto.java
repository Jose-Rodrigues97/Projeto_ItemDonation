package com.projectspring.itemdonation.dtos;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DoacaoDto {
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank
    private String isRetirar;
    
    private Integer conservacaoId;
    
    
}
