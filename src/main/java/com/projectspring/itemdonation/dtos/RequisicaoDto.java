package com.projectspring.itemdonation.dtos;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class RequisicaoDto {
    private UUID doacaoId;
    private Integer status;
    @NotBlank(message = "Nome é obrigatório.")
    private String nomeRequisitante;
    @NotBlank(message = "E-mail é obrigatório.")
    private String emailRequisitante;
    @NotBlank(message = "Telefone é obrigatório.")
    @Size(max = 20)
    private String telefoneRequisitante;
}