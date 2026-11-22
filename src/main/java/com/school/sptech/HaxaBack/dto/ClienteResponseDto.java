package com.school.sptech.HaxaBack.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClienteResponseDto {

    @Schema(description = "Identificador do cliente", example = "1")
    private Integer id;

    @Schema(description = "Nome do cliente", example = "Ana")
    private String nome;

    @Schema(description = "Sobrenome do cliente", example = "Oliveira")
    private String sobrenome;

    @Schema(description = "Telefone do cliente", example = "11987654321")
    private String telefone;

    @Schema(description = "E-mail do cliente", example = "ana.oliveira@exemplo.com")
    private String email;

    @Schema(description = "Indica se o cliente está ativo", example = "true")
    private Boolean ativo;

    @Schema(description = "Data de nascimento do cliente", example = "2005-08-20")
    private LocalDate dataNascimento;

    @Schema(description = "Data e hora em que o cliente foi cadastrado", example = "2026-10-25T10:00:00")
    private LocalDateTime dataCadastro;

    @Schema(description = "Tipo do usuário (0 = Cliente, 1 = Funcionário, 2 = Administrador)", example = "0")
    private Integer tipoUsuario;

    public ClienteResponseDto() {
    }

    public ClienteResponseDto(Integer id, String nome, String sobrenome, String telefone, String email, Boolean ativo, LocalDate dataNascimento, LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.ativo = ativo;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
    }

    public ClienteResponseDto(Integer id, String nome, String sobrenome, String telefone, String email, Boolean ativo, LocalDate dataNascimento, LocalDateTime dataCadastro, Integer tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.ativo = ativo;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
