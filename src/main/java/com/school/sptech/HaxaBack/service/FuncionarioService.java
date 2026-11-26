package com.school.sptech.HaxaBack.service;

import com.school.sptech.HaxaBack.dto.FuncionarioRequestDto;
import com.school.sptech.HaxaBack.entity.Cliente;
import com.school.sptech.HaxaBack.entity.Funcionario;
import com.school.sptech.HaxaBack.exception.ObjetoDuplicadoException;
import com.school.sptech.HaxaBack.exception.ObjetoNaoEncontradoException;
import com.school.sptech.HaxaBack.mapper.FuncionarioMapper;
import com.school.sptech.HaxaBack.repository.ClienteRepository;
import com.school.sptech.HaxaBack.repository.FuncionarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public FuncionarioService(FuncionarioRepository funcionarioRepository,
                              ClienteRepository clienteRepository,
                              PasswordEncoder passwordEncoder) {
        this.funcionarioRepository = funcionarioRepository;
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPorId(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Funcionário não encontrado!"));
    }

    @Transactional
    public Funcionario cadastrar(FuncionarioRequestDto dto) {
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new ObjetoDuplicadoException("E-mail já cadastrado!");
        }

        Cliente usuario = FuncionarioMapper.toUsuario(dto);
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : true);
        usuario.setDataCadastro(LocalDateTime.now());
        usuario = clienteRepository.save(usuario);

        Funcionario funcionario = new Funcionario();
        funcionario.setUsuario(usuario);
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public Funcionario atualizar(Integer id, FuncionarioRequestDto dto) {
        Funcionario funcionario = buscarPorId(id);

        Cliente usuario = funcionario.getUsuario();

        if (clienteRepository.existsByEmailAndIdNot(dto.getEmail(), usuario.getId())) {
            throw new ObjetoDuplicadoException("E-mail já cadastrado!");
        }

        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : usuario.getAtivo());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setTipoUsuario(1);

        clienteRepository.save(usuario);

        return funcionario;
    }

    @Transactional
    public void deletar(Integer id) {
        Funcionario funcionario = buscarPorId(id);

        Cliente usuario = funcionario.getUsuario();

        usuario.setAtivo(false);
        usuario.setTipoUsuario(1);
        clienteRepository.save(usuario);
    }
}

