package com.projectspring.itemdonation.models;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_DOACAO")
public class DoacaoModel implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private boolean retirar;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String estado;
    @ManyToOne
    @JoinColumn(name = "conservacao_id", nullable = false)
    private ConservacaoModel conservacao;
    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private PessoaModel pessoa;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemModel item;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;
    @Column(nullable = false)
    private LocalDateTime dtCriacao;
}