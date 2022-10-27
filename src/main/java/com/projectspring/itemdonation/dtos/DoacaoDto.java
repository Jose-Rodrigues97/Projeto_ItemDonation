package com.projectspring.itemdonation.dtos;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DoacaoDto {
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    private boolean isRetirar;
    private Integer conservacaoId;
    private Integer enderecoId;
    private Integer categoriaId;
    private Integer itemId;
    @NotBlank
    private String pessoaId;
}
