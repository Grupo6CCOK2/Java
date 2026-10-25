package com.school.sptech.HaxaBack.controller;

import com.school.sptech.HaxaBack.dto.AgendamentoRequestDto;
import com.school.sptech.HaxaBack.dto.AgendamentoResponseDto;
import com.school.sptech.HaxaBack.entity.Agendamento;
import com.school.sptech.HaxaBack.mapper.AgendamentoMapper;
import com.school.sptech.HaxaBack.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Agendamentos",description = "Gerenciamento de agendamentos dos clientes")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @Operation(summary = "Busca os agendamentos", description = "Busca todos os agendamentos no sistema")
    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDto>> buscar(){
        List<Agendamento> agendamentos = agendamentoService.buscar();

        return ResponseEntity.status(200).body(AgendamentoMapper.toResponse(agendamentos));
    }

    @Operation(summary = "Busca um agendamento", description = "Busca um agendamento no sistema")
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDto> buscarPorId(@PathVariable Integer id) {
        Agendamento agendamento = agendamentoService.buscarPorId(id);

        return ResponseEntity.status(200).body(AgendamentoMapper.toResponse(agendamento));
    }

    @Operation(summary = "Cadastrar agendamento", description = "Realiza o cadastro de um novo agendamento no sistema")
    @PostMapping
    public ResponseEntity<AgendamentoResponseDto> cadastrar(@Valid @RequestBody AgendamentoRequestDto dto){
        Agendamento agendamento = agendamentoService.cadastrar(AgendamentoMapper.toEntity(dto), dto.getFkUsuario());

        return ResponseEntity.status(201).body(AgendamentoMapper.toResponse(agendamento));
    }

    @Operation(summary = "Atualiza um agendamento", description = "Atualiza um agendamento do sistema")
    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody AgendamentoRequestDto dto){
        Agendamento agendamento = agendamentoService.atualizar(id, AgendamentoMapper.toEntity(dto), dto.getFkUsuario());

        return ResponseEntity.status(200).body(AgendamentoMapper.toResponse(agendamento));
    }

    @Operation(summary = "Deletar um agendamento", description = "Deletar um agendamento do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        agendamentoService.deletar(id);

        return ResponseEntity.status(204).build();
    }
}
