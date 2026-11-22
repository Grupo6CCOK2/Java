package com.school.sptech.HaxaBack.service;

import com.school.sptech.HaxaBack.entity.Agendamento;
import com.school.sptech.HaxaBack.entity.Cliente;
import com.school.sptech.HaxaBack.entity.Dependente;
import com.school.sptech.HaxaBack.entity.DependenteId;
import com.school.sptech.HaxaBack.entity.Funcionario;
import com.school.sptech.HaxaBack.entity.ItemServico;
import com.school.sptech.HaxaBack.entity.Servico;
import com.school.sptech.HaxaBack.entity.StatusAgendamento;
import com.school.sptech.HaxaBack.exception.ObjetoDuplicadoException;
import com.school.sptech.HaxaBack.exception.ObjetoNaoEncontradoException;
import com.school.sptech.HaxaBack.repository.AgendamentoRepository;
import com.school.sptech.HaxaBack.repository.ClienteRepository;
import com.school.sptech.HaxaBack.repository.DependenteRepository;
import com.school.sptech.HaxaBack.repository.FuncionarioRepository;
import com.school.sptech.HaxaBack.repository.ItemServicoRepository;
import com.school.sptech.HaxaBack.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final DependenteRepository dependenteRepository;
    private final ServicoRepository servicoRepository;
    private final ItemServicoRepository itemServicoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              ClienteRepository clienteRepository,
                              FuncionarioRepository funcionarioRepository,
                              DependenteRepository dependenteRepository,
                              ServicoRepository servicoRepository,
                              ItemServicoRepository itemServicoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.dependenteRepository = dependenteRepository;
        this.servicoRepository = servicoRepository;
        this.itemServicoRepository = itemServicoRepository;
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

    @Transactional
    public Agendamento cadastrar(Agendamento agendamento,
                                 Integer clienteId,
                                 Integer funcionarioId,
                                 Integer dependenteId,
                                 List<Integer> servicosIds){
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);

        if (clienteOptional.isEmpty()){
            throw new ObjetoNaoEncontradoException("Cliente não encontrado!");
        }

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Funcionário não encontrado!"));

        Dependente dependente = buscarDependente(dependenteId, clienteId);
        List<Servico> servicos = buscarServicos(servicosIds);

        if (agendamentoRepository.existsByDataAndHorarioAndFuncionarioIdAndStatusNot(
                agendamento.getData(),
                agendamento.getHorario(),
                funcionarioId,
                StatusAgendamento.Cancelado)){
            throw new ObjetoDuplicadoException("Agendamento já existente!");
        }

        agendamento.setFkUsuario(clienteOptional.get());
        agendamento.setFuncionario(funcionario);
        agendamento.setDependente(dependente);

        Agendamento salvo = agendamentoRepository.save(agendamento);
        salvo.setItensServico(salvarItensServico(salvo, servicos));

        return salvo;
    }

    @Transactional
    public Agendamento atualizar(Integer id,
                                 Agendamento agendamento,
                                 Integer clienteId,
                                 Integer funcionarioId,
                                 Integer dependenteId,
                                 List<Integer> servicosIds){
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);

        if (!agendamentoRepository.existsById(id)){
            throw new ObjetoNaoEncontradoException("Agendamento não encontrado!");
        }

        if (clienteOptional.isEmpty()){
            throw new ObjetoNaoEncontradoException("Cliente não encontrado!");
        }

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Funcionário não encontrado!"));

        Dependente dependente = buscarDependente(dependenteId, clienteId);
        List<Servico> servicos = buscarServicos(servicosIds);

        if (agendamentoRepository.existsByDataAndHorarioAndFuncionarioIdAndStatusNotAndIdNot(
                agendamento.getData(),
                agendamento.getHorario(),
                funcionarioId,
                StatusAgendamento.Cancelado,
                id)){
            throw new ObjetoDuplicadoException("Agendamento já existente!");
        }

        agendamento.setFkUsuario(clienteOptional.get());
        agendamento.setFuncionario(funcionario);
        agendamento.setDependente(dependente);
        agendamento.setId(id);

        Agendamento salvo = agendamentoRepository.save(agendamento);
        itemServicoRepository.deleteByAgendamentoId(id);
        itemServicoRepository.flush();
        salvo.setItensServico(salvarItensServico(salvo, servicos));

        return salvo;
    }

    @Transactional
    public void deletar(Integer id){
        if (!agendamentoRepository.existsById(id)){
            throw new ObjetoNaoEncontradoException("Agendamento não encontrado!");
        }

        itemServicoRepository.deleteByAgendamentoId(id);
        itemServicoRepository.flush();
        agendamentoRepository.deleteById(id);
    }

    private Dependente buscarDependente(Integer dependenteId, Integer clienteId) {
        if (dependenteId == null) {
            return null;
        }
        return dependenteRepository.findById(new DependenteId(dependenteId, clienteId))
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Dependente não encontrado!"));
    }

    private List<Servico> buscarServicos(List<Integer> servicosIds) {
        Set<Integer> idsUnicos = new LinkedHashSet<>(servicosIds);
        List<Servico> servicos = new ArrayList<>();
        for (Integer servicoId : idsUnicos) {
            Servico servico = servicoRepository.findById(servicoId)
                    .orElseThrow(() -> new ObjetoNaoEncontradoException("Serviço não encontrado!"));
            servicos.add(servico);
        }
        return servicos;
    }

    private List<ItemServico> salvarItensServico(Agendamento agendamento, List<Servico> servicos) {
        List<ItemServico> itens = new ArrayList<>();
        for (Servico servico : servicos) {
            itens.add(new ItemServico(agendamento, servico, servico.getValor()));
        }
        return itemServicoRepository.saveAll(itens);
    }
}
