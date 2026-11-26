package com.school.sptech.HaxaBack.controller;

import com.school.sptech.HaxaBack.dto.FuncionarioRequestDto;
import com.school.sptech.HaxaBack.dto.FuncionarioResponseDto;
import com.school.sptech.HaxaBack.entity.Funcionario;
import com.school.sptech.HaxaBack.mapper.FuncionarioMapper;
import com.school.sptech.HaxaBack.service.FuncionarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@Tag(name = "Funcionários", description = "Gerenciamento de funcionários")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<FuncionarioResponseDto>> listar() {
        List<FuncionarioResponseDto> funcionarios = funcionarioService.listar().stream()
                .map(FuncionarioMapper::toResponse)
                .toList();
        if (funcionarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FuncionarioResponseDto> buscarPorId(@PathVariable Integer id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok(FuncionarioMapper.toResponse(funcionario));
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FuncionarioResponseDto> cadastrar(@Valid @RequestBody FuncionarioRequestDto dto) {
        Funcionario funcionario = funcionarioService.cadastrar(dto);
        return ResponseEntity.status(201).body(FuncionarioMapper.toResponse(funcionario));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FuncionarioResponseDto> atualizar(@PathVariable Integer id,
                                                             @Valid @RequestBody FuncionarioRequestDto dto) {
        Funcionario funcionario = funcionarioService.atualizar(id, dto);
        return ResponseEntity.ok(FuncionarioMapper.toResponse(funcionario));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

