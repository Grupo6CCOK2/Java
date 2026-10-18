package com.school.sptech.HaxaBack.dto;

import com.school.sptech.HaxaBack.validator.Status;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoRequestDto {

    @NotNull
    @FutureOrPresent
    private LocalDate data;

    @NotNull
    private LocalTime horario;

    @NotNull
    @Status
    private String status;

    @NotNull
    private Integer fkUsuario;

    public AgendamentoRequestDto() {
    }

    public AgendamentoRequestDto(LocalDate data, LocalTime horario, String status, Integer fkUsuario) {
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.fkUsuario = fkUsuario;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
