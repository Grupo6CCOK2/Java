package com.school.sptech.HaxaBack.dto;

import com.school.sptech.HaxaBack.validator.SenhaForte;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class FuncionarioRequestDto {

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
    @Size(max = 45)
    private String email;

    @NotBlank
    @SenhaForte
    @Schema(format = "password", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String senha;

    @NotNull
    private Boolean ativo;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    public FuncionarioRequestDto() {
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }

    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public Boolean getAtivo() { return ativo; }

    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
}

