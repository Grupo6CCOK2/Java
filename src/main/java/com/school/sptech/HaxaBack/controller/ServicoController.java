package com.school.sptech.HaxaBack.controller;

import com.school.sptech.HaxaBack.dto.ServicoRequestDto;
import com.school.sptech.HaxaBack.dto.ServicoResponseDto;
import com.school.sptech.HaxaBack.entity.Servico;
import com.school.sptech.HaxaBack.mapper.ServicoMapper;
import com.school.sptech.HaxaBack.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/servicos")
@Tag(name = "Serviços",description = "Gerenciamento dos Serviços")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @Operation(summary = "Busca os Serviços", description = "Busca todos os serviços no sistema")
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

    @Operation(summary = "Busca um serviço", description = "Busca um serviço no sistema")
    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDto> exibirEspecifico(@PathVariable Integer id) {
        Servico servico = servicoService.buscarPorId(id);
        return ResponseEntity.ok(ServicoMapper.toDto(servico));
    }

    @Operation(summary = "Cadastrar Serviço", description = "Realiza o cadastro de um novo serviço no sistema")
    @PostMapping
    public ResponseEntity<ServicoResponseDto> cadastrar(@PathVariable Integer idCliente, @RequestBody @Valid ServicoRequestDto dto) {
        Servico servico = ServicoMapper.toEntity(dto);
        Servico salvo = servicoService.cadastrarServico(idCliente,servico);
        return ResponseEntity.status(201).body(ServicoMapper.toDto(salvo));
    }

    @Operation(summary = "Atualiza um serviço", description = "Atualiza um serviço do sistema")
    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDto> atualizar(@PathVariable Integer id, @RequestBody @Valid ServicoRequestDto dto) {
        Servico servico = ServicoMapper.toEntity(dto);
        Servico atualizado = servicoService.atualizarServico(id, servico);
        return ResponseEntity.ok(ServicoMapper.toDto(atualizado));
    }

}
