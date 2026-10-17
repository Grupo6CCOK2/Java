package com.school.sptech.HaxaBack.controller;

import com.school.sptech.HaxaBack.dto.ClienteLoginDto;
import com.school.sptech.HaxaBack.dto.ClienteRequestDto;
import com.school.sptech.HaxaBack.dto.ClienteResponseDto;
import com.school.sptech.HaxaBack.dto.ClienteTokenDto;
import com.school.sptech.HaxaBack.entity.Cliente;
import com.school.sptech.HaxaBack.mapper.ClienteMapper;
import com.school.sptech.HaxaBack.service.ClienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    public static final String COOKIE_NOME = "authToken";

    @Value("${jwt.validity}")
    private long jwtValidity;

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteResponseDto>> listar() {
        List<Cliente> clientes = clienteService.buscarClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(ClienteMapper.toResponseDto(clientes));
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ClienteResponseDto> buscarPorId(@PathVariable Integer id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return ResponseEntity.status(200).body(ClienteMapper.toResponseDto(cliente));
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ClienteResponseDto> cadastrar(@Valid @RequestBody ClienteRequestDto dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente salvo = clienteService.cadastarCliente(cliente);
        return ResponseEntity.status(201).body(ClienteMapper.toResponseDto(salvo));
    }

    @PostMapping("/login")
    public ResponseEntity<ClienteTokenDto> login(@RequestBody @Valid ClienteLoginDto clienteLoginDto,
                                                 HttpServletResponse response) {
        final Cliente cliente = ClienteMapper.of(clienteLoginDto);
        ClienteTokenDto autenticado = this.clienteService.autenticar(cliente);

        ResponseCookie cookie = ResponseCookie.from(COOKIE_NOME, autenticado.getToken())
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ofSeconds(jwtValidity))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(autenticado);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(COOKIE_NOME, "")
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ClienteResponseDto> atualizar(@PathVariable Integer id,
                                                      @Valid @RequestBody ClienteRequestDto dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente atualizado = clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.status(200).body(ClienteMapper.toResponseDto(atualizado));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.status(204).build();
    }
}
