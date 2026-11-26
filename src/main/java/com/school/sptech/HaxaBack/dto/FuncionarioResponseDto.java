package com.school.sptech.HaxaBack.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FuncionarioResponseDto {

    private Integer idFuncionario;
    private Integer idUsuario;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private Boolean ativo;
    private LocalDate dataNascimento;
    private LocalDateTime dataCadastro;

    public FuncionarioResponseDto() {
    }

    public Integer getIdFuncionario() { return idFuncionario; }

    public void setIdFuncionario(Integer idFuncionario) { this.idFuncionario = idFuncionario; }

    public Integer getIdUsuario() { return idUsuario; }

    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }

    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Boolean getAtivo() { return ativo; }

    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }

    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
}

