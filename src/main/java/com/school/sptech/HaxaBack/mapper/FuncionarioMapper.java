package com.school.sptech.HaxaBack.mapper;

import com.school.sptech.HaxaBack.dto.FuncionarioRequestDto;
import com.school.sptech.HaxaBack.dto.FuncionarioResponseDto;
import com.school.sptech.HaxaBack.entity.Cliente;
import com.school.sptech.HaxaBack.entity.Funcionario;

public class FuncionarioMapper {

    public static Cliente toUsuario(FuncionarioRequestDto dto) {

        Cliente usuario = new Cliente();
        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setAtivo(dto.getAtivo());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setTipoUsuario(1);

        return usuario;
    }

    public static FuncionarioResponseDto toResponse(Funcionario funcionario) {
        FuncionarioResponseDto dto = new FuncionarioResponseDto();

        dto.setIdFuncionario(funcionario.getId());

        Cliente usuario = funcionario.getUsuario();

        if (usuario != null) {

            dto.setIdUsuario(usuario.getId());
            dto.setNome(usuario.getNome());
            dto.setSobrenome(usuario.getSobrenome());
            dto.setTelefone(usuario.getTelefone());
            dto.setEmail(usuario.getEmail());
            dto.setAtivo(usuario.getAtivo());
            dto.setDataNascimento(usuario.getDataNascimento());
            dto.setDataCadastro(usuario.getDataCadastro());
        }

        return dto;
    }
}

