package com.school.sptech.HaxaBack.service;

import com.school.sptech.HaxaBack.entity.Cliente;
import com.school.sptech.HaxaBack.exception.ObjetoDuplicadoException;
import com.school.sptech.HaxaBack.exception.ObjetoNaoEncontradoException;
import com.school.sptech.HaxaBack.repository.ClienteRepository;import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

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
        if (clienteRepository.existsByNomeAndSobrenome(clienteParaCriar.getNome(), clienteParaCriar.getSobrenome())){
            throw new ObjetoDuplicadoException("Cliente duplicado");
        }

        return clienteRepository.save(clienteParaCriar);
    }

    public Cliente atualizarCliente(Integer id, Cliente clienteParaAtualizar){
        if (!clienteRepository.existsById(id)){
            throw new ObjetoNaoEncontradoException("Cliente não encontrado");
        }

        if (clienteRepository.existsByNomeAndSobrenomeAndIdNot(clienteParaAtualizar.getNome(),  clienteParaAtualizar.getSobrenome(), id)){
            throw new ObjetoDuplicadoException("Cliente duplicado");
        }

        clienteParaAtualizar.setId(id);
        return clienteRepository.save(clienteParaAtualizar);
    }

    public void deletarCliente(Integer id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()){
            throw new ObjetoNaoEncontradoException("Cliente não encontrado");
        }
        clienteOptional.get().setAtivo(false);
    }
}
