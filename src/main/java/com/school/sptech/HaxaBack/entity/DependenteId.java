package com.school.sptech.HaxaBack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DependenteId implements Serializable {

    @Column(name = "idDependentes")
    private Integer idDependentes;

    @Column(name = "Usuario_idUsuario")
    private Integer usuarioId;

    public DependenteId() {
    }

    public DependenteId(Integer idDependentes, Integer usuarioId) {
        this.idDependentes = idDependentes;
        this.usuarioId = usuarioId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DependenteId that = (DependenteId) o;
        return Objects.equals(idDependentes, that.idDependentes)
                && Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDependentes, usuarioId);
    }
}
