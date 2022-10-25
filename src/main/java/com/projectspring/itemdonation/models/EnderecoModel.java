package com.projectspring.itemdonation.models;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_ENDERECO")
public class EnderecoModel implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String logradouro;
    @Column(nullable = false)
    private int numero;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private String pais;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String bairro;
}
