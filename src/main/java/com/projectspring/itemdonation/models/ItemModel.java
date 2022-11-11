package com.projectspring.itemdonation.models;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_ITEM")
public class ItemModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @OneToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaModel categoria;
}