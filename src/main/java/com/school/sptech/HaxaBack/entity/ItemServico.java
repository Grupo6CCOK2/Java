package com.school.sptech.HaxaBack.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "itemServico")
public class ItemServico {

    @Id
    @Column(name = "Agendamento_idAgendamento", nullable = false)
    private Integer agendamentoId;

    @Id
    @Column(name = "Servico_idServico", nullable = false)
    private Integer servicoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Agendamento_idAgendamento", insertable = false, updatable = false, nullable = false)
    private Agendamento agendamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Servico_idServico", insertable = false, updatable = false, nullable = false)
    private Servico servico;

    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private Double precoUnitario;

    public ItemServico() {
    }

    public ItemServico(Agendamento agendamento, Servico servico, Double precoUnitario) {
        this.agendamento = agendamento;
        this.servico = servico;
        this.agendamentoId = agendamento != null ? agendamento.getId() : null;
        this.servicoId = servico != null ? servico.getIdServico() : null;
        this.precoUnitario = precoUnitario;
    }

    public Integer getAgendamentoId() {
        return agendamentoId;
    }

    public void setAgendamentoId(Integer agendamentoId) {
        this.agendamentoId = agendamentoId;
    }

    public Integer getServicoId() {
        return servicoId;
    }

    public void setServicoId(Integer servicoId) {
        this.servicoId = servicoId;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
        this.agendamentoId = agendamento != null ? agendamento.getId() : null;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
        this.servicoId = servico != null ? servico.getIdServico() : null;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public boolean chavesIguais(ItemServico outro) {
        if (outro == null) return false;
        return Objects.equals(agendamentoId, outro.agendamentoId)
                && Objects.equals(servicoId, outro.servicoId);
    }
}