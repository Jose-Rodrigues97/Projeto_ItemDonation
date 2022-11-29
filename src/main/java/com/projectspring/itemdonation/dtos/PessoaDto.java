package com.projectspring.itemdonation.dtos;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaDto {
    @NotBlank(message = "Nome é obrigatório.")
    private String nome;
    @NotBlank(message = "E-mail é obrigatório.")
    private String email;
    @NotBlank(message = "Senha é obrigatório.")
    private String senha;
    @NotBlank(message = "Telefone é obrigatório.")
    @Size(max = 20)
    private String telefone;
}