package com.school.sptech.HaxaBack.mapper;

import com.school.sptech.HaxaBack.dto.AgendamentoRequestDto;
import com.school.sptech.HaxaBack.dto.AgendamentoResponseDto;
import com.school.sptech.HaxaBack.entity.Agendamento;
import com.school.sptech.HaxaBack.entity.ItemServico;

import java.util.Collections;
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

        AgendamentoResponseDto.ClienteResponseDto clienteDto = null;
        if (entity.getFkUsuario() != null) {
            clienteDto = new AgendamentoResponseDto.ClienteResponseDto();
            clienteDto.setId(entity.getFkUsuario().getId());
            clienteDto.setNome(entity.getFkUsuario().getNome());
            clienteDto.setSobrenome(entity.getFkUsuario().getSobrenome());
            clienteDto.setTelefone(entity.getFkUsuario().getTelefone());
            clienteDto.setEmail(entity.getFkUsuario().getEmail());
        }

        AgendamentoResponseDto.FuncionarioResponseDto funcionarioDto = null;
        if (entity.getFuncionario() != null) {
            funcionarioDto = new AgendamentoResponseDto.FuncionarioResponseDto();
            funcionarioDto.setId(entity.getFuncionario().getId());
            if (entity.getFuncionario().getUsuario() != null) {
                funcionarioDto.setUsuarioId(entity.getFuncionario().getUsuario().getId());
                funcionarioDto.setNome(entity.getFuncionario().getUsuario().getNome());
                funcionarioDto.setSobrenome(entity.getFuncionario().getUsuario().getSobrenome());
                funcionarioDto.setEmail(entity.getFuncionario().getUsuario().getEmail());
            }
        }

        AgendamentoResponseDto.DependenteResponseDto dependenteDto = null;
        if (entity.getDependente() != null) {
            dependenteDto = new AgendamentoResponseDto.DependenteResponseDto();
            dependenteDto.setIdDependentes(entity.getDependente().getIdDependentes());
            dependenteDto.setNome(entity.getDependente().getNome());
            dependenteDto.setParentesco(entity.getDependente().getParentesco());
        }

        List<AgendamentoResponseDto.ItemServicoResponseDto> itensDto = Collections.emptyList();
        if (entity.getItensServico() != null) {
            itensDto = entity.getItensServico().stream()
                    .map(AgendamentoMapper::toItemServicoResponse)
                    .toList();
        }

        return new AgendamentoResponseDto(
                entity.getId(),
                entity.getData(),
                entity.getHorario(),
                entity.getStatus(),
                clienteDto,
                funcionarioDto,
                dependenteDto,
                itensDto
        );
    }

    private static AgendamentoResponseDto.ItemServicoResponseDto toItemServicoResponse(ItemServico item) {
        AgendamentoResponseDto.ItemServicoResponseDto itemDto = new AgendamentoResponseDto.ItemServicoResponseDto();
        if (item.getServico() != null) {
            itemDto.setIdServico(item.getServico().getIdServico());
            itemDto.setTipoServico(item.getServico().getTipoServico());
        }
        itemDto.setPrecoUnitario(item.getPrecoUnitario());
        return itemDto;
    }

    public static List<AgendamentoResponseDto> toResponse(List<Agendamento> entities){
        return entities.stream().map(AgendamentoMapper::toResponse).toList();
    }
}