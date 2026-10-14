package com.school.sptech.HaxaBack.controller;

import com.school.sptech.HaxaBack.dto.ServicoRequestDto;
import com.school.sptech.HaxaBack.dto.ServicoResponseDto;
import com.school.sptech.HaxaBack.entity.Servico;
import com.school.sptech.HaxaBack.mapper.ServicoMapper;
import com.school.sptech.HaxaBack.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDto>> exibirTodos() {

        List<Servico> servicos = servicoService.buscarServicos();

        List<ServicoResponseDto> listaDto = new ArrayList<>();

        for (Servico servico : servicos) {
            ServicoResponseDto dto = ServicoMapper.toDto(servico);
            listaDto.add(dto);
        }

        return ResponseEntity.ok(listaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDto> exibirEspecifico(@PathVariable Integer id) {
        Servico servico = servicoService.buscarPorId(id);
        return ResponseEntity.ok(ServicoMapper.toDto(servico));
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDto> cadastrar(@PathVariable Integer idCliente, @RequestBody @Valid ServicoRequestDto dto) {
        Servico servico = ServicoMapper.toEntity(dto);
        Servico salvo = servicoService.cadastrarServico(idCliente,servico);
        return ResponseEntity.status(201).body(ServicoMapper.toDto(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDto> atualizar(@PathVariable Integer id, @RequestBody @Valid ServicoRequestDto dto) {
        Servico servico = ServicoMapper.toEntity(dto);
        Servico atualizado = servicoService.atualizarServico(id, servico);
        return ResponseEntity.ok(ServicoMapper.toDto(atualizado));
    }

}
