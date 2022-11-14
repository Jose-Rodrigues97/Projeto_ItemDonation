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
@Table(name = "TB_REQUISICAO")
public class RequisicaoModel implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID requisicaoId;
    @ManyToOne
    @JoinColumn(name = "doacao_id", nullable = false)
    private DoacaoModel doacao;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;
    @Column(nullable = false)
    private String nomeRequisitante;
    @Column(nullable = false)
    private String emailRequisitante;
    @Column(nullable = false)
    private String telefoneRequisitante;
    @Column(nullable = false)
    private String mensagem;
    @Column(nullable = false)
    private LocalDateTime dtCriacao;
}
