package com.school.sptech.HaxaBack.controller;

import com.school.sptech.HaxaBack.dto.AgendamentoRequestDto;
import com.school.sptech.HaxaBack.dto.AgendamentoResponseDto;
import com.school.sptech.HaxaBack.entity.Agendamento;
import com.school.sptech.HaxaBack.mapper.AgendamentoMapper;
import com.school.sptech.HaxaBack.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDto>> buscar(){
        List<Agendamento> agendamentos = agendamentoService.buscar();

        return ResponseEntity.status(200).body(AgendamentoMapper.toResponse(agendamentos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDto> buscarPorId(@PathVariable Integer id) {
        Agendamento agendamento = agendamentoService.buscarPorId(id);

        return ResponseEntity.status(200).body(AgendamentoMapper.toResponse(agendamento));
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDto> cadastrar(@Valid @RequestBody AgendamentoRequestDto dto){
        Agendamento agendamento = agendamentoService.cadastrar(AgendamentoMapper.toEntity(dto), dto.getFkUsuario());

        return ResponseEntity.status(201).body(AgendamentoMapper.toResponse(agendamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDto> atualizar(@PathVariable Integer id, @Valid @RequestBody AgendamentoRequestDto dto){
        Agendamento agendamento = agendamentoService.atualizar(id, AgendamentoMapper.toEntity(dto), dto.getFkUsuario());

        return ResponseEntity.status(200).body(AgendamentoMapper.toResponse(agendamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        agendamentoService.deletar(id);

        return ResponseEntity.status(204).build();
    }
}
