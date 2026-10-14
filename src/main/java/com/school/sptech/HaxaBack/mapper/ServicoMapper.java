package com.school.sptech.HaxaBack.mapper;

import com.school.sptech.HaxaBack.dto.ServicoRequestDto;
import com.school.sptech.HaxaBack.dto.ServicoResponseDto;
import com.school.sptech.HaxaBack.entity.Servico;

public class ServicoMapper {

    public static Servico toEntity(ServicoRequestDto dto) {
        if (dto == null) return null;
        Servico servico = new Servico();
        servico.setTipoServico(dto.getTipoServico());
        servico.setValor(dto.getValor());
        return servico;
    }

    public static ServicoResponseDto toDto(Servico entity) {
        if (entity == null) return null;
        ServicoResponseDto dto = new ServicoResponseDto();
        dto.setIdServico(entity.getIdServico());
        dto.setTipoServico(entity.getTipoServico());
        dto.setValor(entity.getValor());
        return dto;
    }
}