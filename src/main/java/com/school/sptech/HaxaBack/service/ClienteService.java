package com.school.sptech.HaxaBack.service;

import com.school.sptech.HaxaBack.config.GerenciadorTokenJwt;
import com.school.sptech.HaxaBack.dto.ClienteTokenDto;
import com.school.sptech.HaxaBack.entity.Cliente;
import com.school.sptech.HaxaBack.exception.ObjetoDuplicadoException;
import com.school.sptech.HaxaBack.exception.ObjetoNaoEncontradoException;
import com.school.sptech.HaxaBack.mapper.ClienteMapper;
import com.school.sptech.HaxaBack.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SessaoService sessaoService;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> buscarClientes(){
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()){
            throw new ObjetoNaoEncontradoException("Cliente não encontrado");
        }
        return clienteOptional.get();
    }

    public Cliente cadastarCliente(Cliente clienteParaCriar) {
        if (clienteRepository.existsByEmail(clienteParaCriar.getEmail())){
            throw new ObjetoDuplicadoException("Cliente duplicado");
        }

        String senhaCriptografada = passwordEncoder.encode(clienteParaCriar.getSenha());
        clienteParaCriar.setSenha(senhaCriptografada);

        return clienteRepository.save(clienteParaCriar);
    }

    @Transactional
    public ClienteTokenDto autenticar(Cliente cliente) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                cliente.getEmail(), cliente.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Cliente clienteAutenticado =
                clienteRepository.findByEmail(cliente.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jti = UUID.randomUUID().toString();
        sessaoService.criar(clienteAutenticado, jti);

        final String token = gerenciadorTokenJwt.generateToken(authentication, jti);

        return ClienteMapper.of(clienteAutenticado, token);
    }

    public Cliente atualizarCliente(Integer id, Cliente clienteParaAtualizar){
        if (!clienteRepository.existsById(id)){
            throw new ObjetoNaoEncontradoException("Cliente não encontrado");
        }

        if (clienteRepository.existsByEmailAndIdNot(clienteParaAtualizar.getEmail(), id)){
            throw new ObjetoDuplicadoException("Cliente duplicado");
        }

        String senhaCriptografada = passwordEncoder.encode(clienteParaAtualizar.getSenha());
        clienteParaAtualizar.setSenha(senhaCriptografada);

        clienteParaAtualizar.setId(id);
        return clienteRepository.save(clienteParaAtualizar);
    }

    @Transactional
    public void deletarCliente(Integer id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()){
            throw new ObjetoNaoEncontradoException("Cliente não encontrado");
        }
        Cliente cliente = clienteOptional.get();
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }
}
