package com.school.sptech.HaxaBack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServico;
    private String tipoServico;
    private Double valor;


    public Integer getIdServico() { return idServico; }
    public void setIdServico(Integer idServico) { this.idServico = idServico; }

    public String getTipoServico() { return tipoServico; }
    public void setTipoServico(String tipoServico) { this.tipoServico = tipoServico; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }


}