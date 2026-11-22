package com.school.sptech.HaxaBack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemServicoId implements Serializable {

    @Column(name = "Agendamento_idAgendamento")
    private Integer agendamentoId;

    @Column(name = "Servico_idServico")
    private Integer servicoId;

    public ItemServicoId() {
    }

    public ItemServicoId(Integer agendamentoId, Integer servicoId) {
        this.agendamentoId = agendamentoId;
        this.servicoId = servicoId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemServicoId that = (ItemServicoId) o;
        return Objects.equals(agendamentoId, that.agendamentoId)
                && Objects.equals(servicoId, that.servicoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agendamentoId, servicoId);
    }
}
