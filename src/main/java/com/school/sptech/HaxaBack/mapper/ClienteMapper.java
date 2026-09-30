package com.school.sptech.HaxaBack.mapper;

import com.school.sptech.HaxaBack.dto.ClienteRequestDto;
import com.school.sptech.HaxaBack.dto.ClienteResponseDto;
import com.school.sptech.HaxaBack.entity.Cliente;
import java.util.List;

public class ClienteMapper {
    public static Cliente toEntity(ClienteRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setSobrenome(dto.getSobrenome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());
        cliente.setAtivo(dto.getAtivo());
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setDataCadastro(dto.getDataCadastro());
        return cliente;
    }

    public static ClienteResponseDto toResponseDto(Cliente entity) {
        if (entity == null) {
            return null;
        }

        ClienteResponseDto dto = new ClienteResponseDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setSobrenome(entity.getSobrenome());
        dto.setTelefone(entity.getTelefone());
        dto.setEmail(entity.getEmail());
        dto.setAtivo(entity.getAtivo());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setDataCadastro(entity.getDataCadastro());
        return dto;
    }

    public static List<ClienteResponseDto> toResponseDto(List<Cliente> entities) {
        return entities.stream()
                .map(ClienteMapper::toResponseDto)
                .toList();
    }
}
