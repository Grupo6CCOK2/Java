package com.school.sptech.HaxaBack.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoResponseDto {

    @Schema(description = "Identificador do agendamento", example = "1")
    private Integer id;

    @Schema(description = "Data do agendamento", example = "2027-01-15")
    private LocalDate data;

    @Schema(description = "Horário do agendamento", example = "14:30:00")
    private LocalTime horario;

    @Schema(description = "Status atual do agendamento", example = "pendente", allowableValues = {
            "pendente",
            "confirmado",
            "concluído",
            "cancelado"
    })
    private String status;

    @Schema(description = "Dados do cliente associado ao agendamento")
    private ClienteResponseDto cliente;

    public static class ClienteResponseDto {

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
