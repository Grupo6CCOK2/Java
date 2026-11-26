package com.school.sptech.HaxaBack.dto;

import com.school.sptech.HaxaBack.entity.StatusAgendamento;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AgendamentoResponseDto {

    @Schema(description = "Identificador do agendamento", example = "1")
    private Integer id;

    @Schema(description = "Data do agendamento", example = "2027-01-15")
    private LocalDate data;

    @Schema(description = "Horário do agendamento", example = "14:30:00")
    private LocalTime horario;

    @Schema(description = "Status atual do agendamento", example = "Pendente")
    private StatusAgendamento status;

    @Schema(description = "Dados do cliente associado ao agendamento")
    private ClienteResponseDto cliente;

    @Schema(description = "Dados do funcionário responsável pelo agendamento")
    private FuncionarioResponseDto funcionario;

    @Schema(description = "Dados do dependente associado ao agendamento (quando aplicável)")
    private DependenteResponseDto dependente;

    @Schema(description = "Serviços associados ao agendamento")
    private List<ItemServicoResponseDto> itensServico;

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

    public static class FuncionarioResponseDto {

        @Schema(description = "Identificador do funcionário", example = "1")
        private Integer id;

        @Schema(description = "Identificador do usuário do funcionário", example = "2")
        private Integer usuarioId;

        @Schema(description = "Nome do funcionário", example = "Carlos")
        private String nome;

        @Schema(description = "Sobrenome do funcionário", example = "Oliveira")
        private String sobrenome;

        @Schema(description = "E-mail do funcionário", example = "carlos.oli@email.com")
        private String email;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(Integer usuarioId) {
            this.usuarioId = usuarioId;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class DependenteResponseDto {

        @Schema(description = "Identificador do dependente", example = "1")
        private Integer idDependentes;

        @Schema(description = "Nome do dependente", example = "Lucas Silva")
        private String nome;

        @Schema(description = "Parentesco com o titular", example = "Filho")
        private String parentesco;

        public Integer getIdDependentes() {
            return idDependentes;
        }

        public void setIdDependentes(Integer idDependentes) {
            this.idDependentes = idDependentes;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getParentesco() {
            return parentesco;
        }

        public void setParentesco(String parentesco) {
            this.parentesco = parentesco;
        }
    }

    public static class ItemServicoResponseDto {

        @Schema(description = "Identificador do serviço", example = "1")
        private Integer idServico;

        @Schema(description = "Tipo do serviço", example = "Corte de Cabelo")
        private String tipoServico;

        @Schema(description = "Preço unitário aplicado no agendamento", example = "50.00")
        private Double precoUnitario;

        public Integer getIdServico() {
            return idServico;
        }

        public void setIdServico(Integer idServico) {
            this.idServico = idServico;
        }

        public String getTipoServico() {
            return tipoServico;
        }

        public void setTipoServico(String tipoServico) {
            this.tipoServico = tipoServico;
        }

        public Double getPrecoUnitario() {
            return precoUnitario;
        }

        public void setPrecoUnitario(Double precoUnitario) {
            this.precoUnitario = precoUnitario;
        }
    }

    public AgendamentoResponseDto() {
    }

    public AgendamentoResponseDto(Integer id, LocalDate data, LocalTime horario, StatusAgendamento status, ClienteResponseDto cliente, FuncionarioResponseDto funcionario, DependenteResponseDto dependente, List<ItemServicoResponseDto> itensServico) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.dependente = dependente;
        this.itensServico = itensServico;
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

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }

    public ClienteResponseDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDto cliente) {
        this.cliente = cliente;
    }

    public FuncionarioResponseDto getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioResponseDto funcionario) {
        this.funcionario = funcionario;
    }

    public DependenteResponseDto getDependente() {
        return dependente;
    }

    public void setDependente(DependenteResponseDto dependente) {
        this.dependente = dependente;
    }

    public List<ItemServicoResponseDto> getItensServico() {
        return itensServico;
    }

    public void setItensServico(List<ItemServicoResponseDto> itensServico) {
        this.itensServico = itensServico;
    }
}