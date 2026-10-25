package com.school.sptech.HaxaBack.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ServicoResponseDto {

    @Schema(description = "Identificador do serviço", example = "1")
    private Integer idServico;

    @Schema(description = "Nome ou tipo do serviço", example = "Corte de cabelo")
    private String tipoServico;

    @Schema(description = "Valor cobrado pelo serviço", example = "50.00")
    private Double valor;

    public Integer getIdServico() { return idServico; }
    public void setIdServico(Integer idServico) { this.idServico = idServico; }

    public String getTipoServico() { return tipoServico; }
    public void setTipoServico(String tipoServico) { this.tipoServico = tipoServico; }

    public Double getValor() { return valor; }
    public void setValor(
            Double valor) { this.valor = valor; }

}