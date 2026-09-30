package com.school.sptech.HaxaBack.dto;

import com.school.sptech.HaxaBack.validator.SenhaForte;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ClienteRequestDto {
    @NotBlank
    @Size(max = 45)
    private String nome;

    @NotBlank
    @Size(max = 45)
    private String sobrenome;

    @NotBlank
    @Size(min = 11, max = 11)
    private String telefone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @SenhaForte
    private String senha;

    @NotNull
    private Boolean ativo;

    @NotNull
    @Past
    private LocalDate dataNascimento;

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
