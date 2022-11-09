package com.projectspring.itemdonation.models;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_DOACAO")
public class DoacaoModel implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID doacaoId;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private boolean retirar;
    @Column(nullable = false)
    private Integer status;
    @Column(nullable = false)
    private Integer conservacaoId;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private Integer categoriaId;
    @Column(nullable = false)
    private Integer itemId;
    @Column(nullable = false)
    private UUID pessoaId;
    @Column(nullable = false)
    private LocalDateTime dtCriacao;
}