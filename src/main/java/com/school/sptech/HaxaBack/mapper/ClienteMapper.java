package com.school.sptech.HaxaBack.mapper;

import com.school.sptech.HaxaBack.dto.ClienteLoginDto;
import com.school.sptech.HaxaBack.dto.ClienteRequestDto;
import com.school.sptech.HaxaBack.dto.ClienteResponseDto;
import com.school.sptech.HaxaBack.dto.ClienteSessaoDto;
import com.school.sptech.HaxaBack.dto.ClienteTokenDto;
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
        cliente.setTipoUsuario(dto.getTipoUsuario());
        return cliente;
    }

    public static Cliente of(ClienteRequestDto clienteCriacaoDto) {
        return toEntity(clienteCriacaoDto);
    }

    public static Cliente of(ClienteLoginDto clienteLoginDto) {
        Cliente cliente = new Cliente();

        cliente.setEmail(clienteLoginDto.getEmail());
        cliente.setSenha(clienteLoginDto.getSenha());

        return cliente;
    }

    public static ClienteTokenDto of(Cliente cliente, String token) {
        ClienteTokenDto clienteTokenDto = new ClienteTokenDto();

        clienteTokenDto.setUserId(cliente.getId());
        clienteTokenDto.setEmail(cliente.getEmail());
        clienteTokenDto.setNome(cliente.getNome());
        clienteTokenDto.setToken(token);

        return clienteTokenDto;
    }

    public static ClienteSessaoDto ofSessao(ClienteTokenDto tokenDto) {
        ClienteSessaoDto dto = new ClienteSessaoDto();

        dto.setUserId(tokenDto.getUserId());
        dto.setEmail(tokenDto.getEmail());
        dto.setNome(tokenDto.getNome());

        return dto;
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
        dto.setTipoUsuario(entity.getTipoUsuario());
        return dto;
    }

    public static ClienteResponseDto of(Cliente cliente) {
        return toResponseDto(cliente);
    }

    public static List<ClienteResponseDto> toResponseDto(List<Cliente> entities) {
        return entities.stream()
                .map(ClienteMapper::toResponseDto)
                .toList();
    }
}
