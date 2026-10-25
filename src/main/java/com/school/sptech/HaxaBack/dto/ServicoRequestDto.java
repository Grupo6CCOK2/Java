package com.school.sptech.HaxaBack.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ServicoRequestDto {

    @Schema(description = "Nome ou tipo do serviço", example = "Corte de cabelo")
    private String tipoServico;

    @Schema(description = "Valor cobrado pelo serviço", example = "50.00")
    private Double valor;

    public String getTipoServico() { return tipoServico; }
    public void setTipoServico(String tipoServico) { this.tipoServico = tipoServico; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
}