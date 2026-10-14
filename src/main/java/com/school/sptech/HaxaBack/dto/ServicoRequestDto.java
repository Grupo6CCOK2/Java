package com.school.sptech.HaxaBack.dto;

public class ServicoRequestDto {
    private String tipoServico;
    private Double valor;

    public String getTipoServico() { return tipoServico; }
    public void setTipoServico(String tipoServico) { this.tipoServico = tipoServico; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
}