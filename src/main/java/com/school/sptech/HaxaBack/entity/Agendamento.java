package com.school.sptech.HaxaBack.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate data;
    private LocalTime horario;
    private String status;
    @ManyToOne
    private Cliente fkUsuario;

    public Agendamento() {
    }

    public Agendamento(Integer id, LocalDate data, LocalTime horario, String status, Cliente fkUsuario) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.fkUsuario = fkUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Cliente getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Cliente fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
