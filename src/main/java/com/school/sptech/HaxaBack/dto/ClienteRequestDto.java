package com.school.sptech.HaxaBack.dto;

import com.school.sptech.HaxaBack.validator.SenhaForte;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ClienteRequestDto {

    @Schema(description = "Nome do cliente", example = "Ana")
    @NotBlank
    @Size(max = 45)
    private String nome;

    @Schema(description = "Sobrenome do cliente", example = "Oliveira")
    @NotBlank
    @Size(max = 45)
    private String sobrenome;

    @Schema(description = "Telefone do cliente com 11 caracteres", example = "11987654321")
    @NotBlank
    @Size(min = 11, max = 11)
    private String telefone;

    @Schema(description = "E-mail do cliente", example = "ana.oliveira@exemplo.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Senha informada pelo cliente", example = "Exemplo123", format = "password", accessMode = Schema.AccessMode.WRITE_ONLY)
    @NotBlank
    @SenhaForte
    private String senha;

    @Schema(description = "Indica se o cliente está ativo", example = "true")
    @NotNull
    private Boolean ativo;

    @Schema(description = "Data de nascimento do cliente", example = "2005-08-20")
    @NotNull
    @Past
    private LocalDate dataNascimento;

    @Schema(description = "Data de cadastro do cliente", example = "2026-10-25")
    @NotNull
    private LocalDate dataCadastro;

    public ClienteRequestDto() {
    }

    public ClienteRequestDto(String nome, String sobrenome, String telefone, String email, String senha, Boolean ativo, LocalDate dataNascimento, LocalDate dataCadastro) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
