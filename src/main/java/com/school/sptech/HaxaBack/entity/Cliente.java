package com.school.sptech.HaxaBack.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuario")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer id;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 45)
    private String sobrenome;

    @Column(name = "telefone", length = 11)
    private String telefone;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "senha", nullable = false, length = 60)
    private String senha;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "dataCadastro")
    private LocalDateTime dataCadastro;

    @Column(name = "tipo_usuario", columnDefinition = "TINYINT(1)")
    private Integer tipoUsuario;

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String sobrenome, String telefone, String email, String senha, Boolean ativo, LocalDate dataNascimento, LocalDateTime dataCadastro, Integer tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
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
