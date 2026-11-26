package com.school.sptech.HaxaBack.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Dependentes")
public class Dependente {

    @Id
    @Column(name = "idDependentes")
    private Integer idDependentes;

    @Id
    @Column(name = "Usuario_idUsuario", nullable = false)
    private Integer usuarioId;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "parentesco", nullable = false, length = 45)
    private String parentesco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_idUsuario", insertable = false, updatable = false, nullable = false)
    private Cliente usuario;

    public Dependente() {
    }

    public Dependente(Integer idDependentes, Integer usuarioId, String nome, String parentesco, Cliente usuario) {
        this.idDependentes = idDependentes;
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.parentesco = parentesco;
        this.usuario = usuario;
    }

    public Integer getIdDependentes() {
        return idDependentes;
    }

    public void setIdDependentes(Integer idDependentes) {
        this.idDependentes = idDependentes;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
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
        this.usuarioId = usuario != null ? usuario.getId() : null;
    }

    public boolean chavesIguais(Dependente outro) {
        if (outro == null) return false;
        return Objects.equals(idDependentes, outro.idDependentes)
                && Objects.equals(usuarioId, outro.usuarioId);
    }
}