package com.projectspring.itemdonation.models;
import java.io.Serializable;
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
@Table(name = "TB_IMAGEM_DOACAO")
public class ImagemDoacaoModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer imagemId;
    @Column(nullable = false)
    private byte binario;
    @Column(nullable = false)
    private UUID doacaoId;
}