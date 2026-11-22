package com.school.sptech.HaxaBack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFuncionario")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "Usuario_idUsuario", nullable = false, unique = true)
    private Cliente usuario;

    public Funcionario() {
    }

    public Funcionario(Integer id, Cliente usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getUsuario() {
        return usuario;
    }

    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }
}
