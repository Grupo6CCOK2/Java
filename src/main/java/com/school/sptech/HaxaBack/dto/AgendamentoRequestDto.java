package com.school.sptech.HaxaBack.dto;

import com.school.sptech.HaxaBack.entity.StatusAgendamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AgendamentoRequestDto {

    @Schema(description = "Data do agendamento", example = "2027-01-15")
    @NotNull
    @FutureOrPresent
    private LocalDate data;

    @Schema(description = "Horário do agendamento", example = "14:30:00")
    @NotNull
    private LocalTime horario;

    @Schema(description = "Situação atual do agendamento", example = "Pendente")
    @NotNull
    private StatusAgendamento status;

    @Schema(description = "ID do cliente responsável pelo agendamento", example = "1")
    @NotNull
    private Integer fkUsuario;

    @Schema(description = "ID do funcionário responsável pelo atendimento", example = "1")
    @NotNull
    private Integer fkFuncionario;

    @Schema(description = "ID do dependente atendido (opcional)", example = "1")
    private Integer fkDependente;

    @Schema(description = "IDs dos serviços associados ao agendamento", example = "[1]")
    @NotEmpty
    private List<Integer> servicosIds;

    public AgendamentoRequestDto() {
    }

    public AgendamentoRequestDto(LocalDate data, LocalTime horario, StatusAgendamento status, Integer fkUsuario, Integer fkFuncionario, Integer fkDependente, List<Integer> servicosIds) {
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.fkUsuario = fkUsuario;
        this.fkFuncionario = fkFuncionario;
        this.fkDependente = fkDependente;
        this.servicosIds = servicosIds;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Integer getFkFuncionario() {
        return fkFuncionario;
    }

    public void setFkFuncionario(Integer fkFuncionario) {
        this.fkFuncionario = fkFuncionario;
    }

    public Integer getFkDependente() {
        return fkDependente;
    }

    public void setFkDependente(Integer fkDependente) {
        this.fkDependente = fkDependente;
    }

    public List<Integer> getServicosIds() {
        return servicosIds;
    }

    public void setServicosIds(List<Integer> servicosIds) {
        this.servicosIds = servicosIds;
    }
}
