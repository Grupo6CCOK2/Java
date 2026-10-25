package com.school.sptech.HaxaBack.controller;

import com.school.sptech.HaxaBack.dto.ClienteRequestDto;
import com.school.sptech.HaxaBack.dto.ClienteResponseDto;
import com.school.sptech.HaxaBack.entity.Cliente;
import com.school.sptech.HaxaBack.mapper.ClienteMapper;
import com.school.sptech.HaxaBack.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes",description = "Gerenciamento de clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Busca os clientes", description = "Busca todos os clientes no sistema")
    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listar() {
        List<Cliente> clientes = clienteService.buscarClientes();
        return ResponseEntity.status(200).body(ClienteMapper.toResponseDto(clientes));
    }

    @Operation(summary = "Busca um cliente", description = "Busca um cliente no sistema")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> buscarPorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return ResponseEntity.status(200).body(ClienteMapper.toResponseDto(cliente));
    }
    @Operation(summary = "Cadastrar cliente", description = "Realiza o cadastro de um novo cliente no sistema")
    @PostMapping
    public ResponseEntity<ClienteResponseDto> cadastrar(@Valid @RequestBody ClienteRequestDto dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente salvo = clienteService.cadastarCliente(cliente);
        return ResponseEntity.status(201).body(ClienteMapper.toResponseDto(salvo));
    }

    @Operation(summary = "Atualiza um cliente", description = "Atualiza um cliente do sistema")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> atualizar(@PathVariable Integer id,
                                                      @Valid @RequestBody ClienteRequestDto dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente atualizado = clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.status(200).body(ClienteMapper.toResponseDto(atualizado));
    }

    @Operation(summary = "Deletar um cliente", description = "Deletar um cliente do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.status(204).build();
    }
}
