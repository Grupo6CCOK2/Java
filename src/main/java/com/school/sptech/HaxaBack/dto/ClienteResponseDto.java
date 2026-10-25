package com.school.sptech.HaxaBack.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

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

    @Schema(description = "Data em que o cliente foi cadastrado", example = "2026-10-25")
    private LocalDate dataCadastro;

    public ClienteResponseDto() {
    }

    public ClienteResponseDto(Integer id, String nome, String sobrenome, String telefone, String email, Boolean ativo, LocalDate dataNascimento, LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.ativo = ativo;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
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

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
