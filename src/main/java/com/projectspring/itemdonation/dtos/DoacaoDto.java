package com.projectspring.itemdonation.dtos;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DoacaoDto {
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    private boolean retirar;
    private Integer conservacaoId;
    private Integer enderecoId;
    private Integer categoriaId;
    private Integer itemId;
    private UUID pessoaId;
    private Integer status;
    @NotBlank
    private String endereco;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;
}
