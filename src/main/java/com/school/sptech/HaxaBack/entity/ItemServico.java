package com.school.sptech.HaxaBack.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itemServico")
public class ItemServico {

    @EmbeddedId
    private ItemServicoId id = new ItemServicoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("agendamentoId")
    @JoinColumn(name = "Agendamento_idAgendamento", nullable = false)
    private Agendamento agendamento;

    @ManyToOne
    @MapsId("servicoId")
    @JoinColumn(name = "Servico_idServico", nullable = false)
    private Servico servico;

    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    public ItemServico() {
    }

    public ItemServico(Agendamento agendamento, Servico servico, BigDecimal precoUnitario) {
        this.id = new ItemServicoId(
                agendamento != null ? agendamento.getId() : null,
                servico != null ? servico.getIdServico() : null
        );
        this.agendamento = agendamento;
        this.servico = servico;
        this.precoUnitario = precoUnitario;
    }

    public ItemServicoId getId() {
        return id;
    }

    public void setId(ItemServicoId id) {
        this.id = id;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
