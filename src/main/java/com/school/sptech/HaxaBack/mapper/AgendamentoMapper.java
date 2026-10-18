package com.school.sptech.HaxaBack.mapper;

import com.school.sptech.HaxaBack.dto.AgendamentoRequestDto;
import com.school.sptech.HaxaBack.dto.AgendamentoResponseDto;
import com.school.sptech.HaxaBack.entity.Agendamento;

import java.util.List;

public class AgendamentoMapper {

    public static Agendamento toEntity(AgendamentoRequestDto dto) {
        if (dto == null){
            return null;
        }

        Agendamento agendamento = new Agendamento();

        agendamento.setData(dto.getData());
        agendamento.setHorario(dto.getHorario());
        agendamento.setStatus(dto.getStatus());

        return agendamento;
    }

    public static AgendamentoResponseDto toResponse(Agendamento entity){
        if (entity == null){
            return null;
        }

        AgendamentoResponseDto.ClienteResponseDto clienteDto = new AgendamentoResponseDto.ClienteResponseDto();
        clienteDto.setId(entity.getFkUsuario().getId());
        clienteDto.setNome(entity.getFkUsuario().getNome());
        clienteDto.setSobrenome(entity.getFkUsuario().getSobrenome());
        clienteDto.setTelefone(entity.getFkUsuario().getTelefone());
        clienteDto.setEmail(entity.getFkUsuario().getEmail());

        AgendamentoResponseDto response = new AgendamentoResponseDto(entity.getId(), entity.getData(), entity.getHorario(), entity.getStatus(), clienteDto);

        return response;
    }

    public static List<AgendamentoResponseDto> toResponse(List<Agendamento> entities){
        return entities.stream().map(AgendamentoMapper::toResponse).toList();
    }
}
