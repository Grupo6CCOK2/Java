package com.school.sptech.HaxaBack.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoResponseDto {

    private Integer id;
    private LocalDate data;
    private LocalTime horario;
    private String status;
    private ClienteResponseDto cliente;

    public static class ClienteResponseDto{
        private Integer id;
        private String nome;
        private String sobrenome;
        private String telefone;
        private String email;

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
    }

    public AgendamentoResponseDto() {
    }

    public AgendamentoResponseDto(Integer id, LocalDate data, LocalTime horario, String status, ClienteResponseDto cliente) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ClienteResponseDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDto cliente) {
        this.cliente = cliente;
    }
}
