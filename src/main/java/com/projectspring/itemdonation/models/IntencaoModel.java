package com.projectspring.itemdonation.models;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_INTENCAO")
public class IntencaoModel implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String isRetirar;
    @Column(nullable = false)
    private Integer conservacaoId;
    @Column(nullable = false)
    private Integer enderecoId;
    @Column(nullable = false)
    private Integer categoriaId;
    @Column(nullable = false)
    private Integer itemId;
    @Column(nullable = false)
    private UUID pessoaId;
    @Column(nullable = false)
    private LocalDateTime dtCriacao;
}
