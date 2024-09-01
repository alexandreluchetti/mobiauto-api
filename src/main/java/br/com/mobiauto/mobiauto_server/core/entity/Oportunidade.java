package br.com.mobiauto.mobiauto_server.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@Table(name = "oportunidade")
public class Oportunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(length = 255)
    private String motivoConclusao;

    @Column(nullable = false)
    private java.sql.Date dataAtribuicao;

    private java.sql.Date dataConclusao;

    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Veiculo veiculo;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Revenda revenda;

    public void update(Oportunidade oportunidade) {
        this.id = oportunidade.getId();
        this.dataAtribuicao = oportunidade.getDataAtribuicao();
        this.cliente = oportunidade.getCliente();
        this.usuario = oportunidade.getUsuario();
        this.veiculo = oportunidade.getVeiculo();
        this.revenda = oportunidade.getRevenda();
    }
}