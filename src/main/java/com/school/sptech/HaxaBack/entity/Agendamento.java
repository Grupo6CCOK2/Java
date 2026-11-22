package com.school.sptech.HaxaBack.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAgendamento")
    private Integer id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "horario", nullable = false)
    private LocalTime horario;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('Pendente', 'Confirmado', 'Cancelado', 'Concluido')")
    private StatusAgendamento status;

    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    private Cliente fkUsuario;

    @ManyToOne
    @JoinColumn(name = "Funcionario_idFuncionario", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "Dependentes_idDependentes", referencedColumnName = "idDependentes"),
            @JoinColumn(name = "Dependentes_Usuario_idUsuario", referencedColumnName = "Usuario_idUsuario")
    })
    private Dependente dependente;

    @OneToMany(mappedBy = "agendamento")
    private List<ItemServico> itensServico = new ArrayList<>();

    public Agendamento() {
    }

    public Agendamento(Integer id, LocalDate data, LocalTime horario, StatusAgendamento status, Cliente fkUsuario, Funcionario funcionario, Dependente dependente) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.fkUsuario = fkUsuario;
        this.funcionario = funcionario;
        this.dependente = dependente;
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

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    public Cliente getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Cliente fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }

    public List<ItemServico> getItensServico() {
        return itensServico;
    }

    public void setItensServico(List<ItemServico> itensServico) {
        this.itensServico = itensServico;
    }
}
