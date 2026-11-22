package com.school.sptech.HaxaBack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Dependentes")
public class Dependente {

    @EmbeddedId
    private DependenteId id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "parentesco", nullable = false, length = 45)
    private String parentesco;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("usuarioId")
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    private Cliente usuario;

    public Dependente() {
    }

    public Dependente(DependenteId id, String nome, String parentesco, Cliente usuario) {
        this.id = id;
        this.nome = nome;
        this.parentesco = parentesco;
        this.usuario = usuario;
    }

    public DependenteId getId() {
        return id;
    }

    public void setId(DependenteId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Cliente getUsuario() {
        return usuario;
    }

    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }
}
