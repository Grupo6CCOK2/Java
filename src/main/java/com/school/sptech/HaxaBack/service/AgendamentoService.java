package com.school.sptech.HaxaBack.service;

import com.school.sptech.HaxaBack.entity.Agendamento;
import com.school.sptech.HaxaBack.entity.Cliente;
import com.school.sptech.HaxaBack.exception.ObjetoDuplicadoException;
import com.school.sptech.HaxaBack.exception.ObjetoNaoEncontradoException;
import com.school.sptech.HaxaBack.repository.AgendamentoRepository;
import com.school.sptech.HaxaBack.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, ClienteRepository clienteRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Agendamento> buscar(){
        List<Agendamento> agendamentos = agendamentoRepository.findAll();

        return agendamentos;
    }

    public Agendamento buscarPorId(Integer id){
        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(id);

        if (agendamentoOptional.isEmpty()){
            throw new ObjetoNaoEncontradoException("Agendamento não encontrado!");
        }

        return agendamentoOptional.get();
    }

    public Agendamento cadastrar(Agendamento agendamento, Integer clienteId){
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);

        if (clienteOptional.isEmpty()){
            throw new ObjetoNaoEncontradoException("Cliente não encontrado!");
        }

        Cliente cliente = clienteOptional.get();

        if (agendamentoRepository.existsByDataAndHorario(agendamento.getData(), agendamento.getHorario())){
            throw new ObjetoDuplicadoException("Agendamento já existente!");
        }

        agendamento.setFkUsuario(cliente);

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento atualizar(Integer id, Agendamento agendamento, Integer clienteId){
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);

        if (agendamentoRepository.existsById(id)){
            throw new ObjetoNaoEncontradoException("Agendamento não encontrado!");
        }

        if (clienteOptional.isEmpty()){
            throw new ObjetoNaoEncontradoException("Cliente não encontrado!");
        }

        if (agendamentoRepository.existsByDataAndHorarioAndIdNot(agendamento.getData(), agendamento.getHorario(), id)){
            throw new ObjetoDuplicadoException("Agendamento já existente!");
        }

        agendamento.setFkUsuario(clienteOptional.get());
        agendamento.setId(id);

        return agendamentoRepository.save(agendamento);
    }

    public void deletar(Integer id){
        if (!agendamentoRepository.existsById(id)){
            throw new ObjetoNaoEncontradoException("Agendamento não encontrado!");
        }

        agendamentoRepository.deleteById(id);
    }
}
