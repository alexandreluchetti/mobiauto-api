package br.com.mobiauto.mobiauto_server.core.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String marca;

    @Column(nullable = false, length = 255)
    private String modelo;

    @Column(nullable = false, length = 255)
    private String versao;

    @Column(nullable = false)
    private Integer anoModelo;
}