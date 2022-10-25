package com.projectspring.itemdonation.dtos;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IntencaoDto {
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotBlank
    private String isRetirar;
    @NotBlank
    private Integer conservacaoId;
    @NotBlank
    private Integer enderecoId;
    @NotBlank
    private Integer categoriaId;
    @NotBlank
    private Integer itemId;
    @NotBlank
    private String pessoaId;

}
