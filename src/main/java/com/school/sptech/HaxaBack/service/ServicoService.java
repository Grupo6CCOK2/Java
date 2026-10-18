package com.school.sptech.HaxaBack.service;

import com.school.sptech.HaxaBack.entity.Cliente;
import com.school.sptech.HaxaBack.entity.Servico;
import com.school.sptech.HaxaBack.exception.ObjetoDuplicadoException;
import com.school.sptech.HaxaBack.exception.ObjetoNaoEncontradoException;
import com.school.sptech.HaxaBack.repository.ClienteRepository;
import com.school.sptech.HaxaBack.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> buscarServicos() {
        return servicoRepository.findAll();
    }

    public Servico buscarPorId(Integer id) {
        Optional<Servico> servicoOptional = servicoRepository.findById(id);
        if (servicoOptional.isEmpty()) {
            throw new ObjetoNaoEncontradoException("Serviço não encontrado");
        }
        return servicoOptional.get();
    }

    public Servico cadastrarServico(Servico servicoParaCriar) {
        if (servicoRepository.existsByTipoServico(servicoParaCriar.getTipoServico())) {
            throw new ObjetoDuplicadoException("Serviço duplicado");
        }

        return servicoRepository.save(servicoParaCriar);
    }

    public Servico atualizarServico(Integer id, Servico servicoParaAtualizar) {
        if (!servicoRepository.existsById(id)) {
            throw new ObjetoNaoEncontradoException("Serviço não encontrado");
        }

        if (servicoRepository.existsByTipoServicoAndIdServicoNot(servicoParaAtualizar.getTipoServico(), id)) {
            throw new ObjetoDuplicadoException("Serviço duplicado");
        }

        servicoParaAtualizar.setIdServico(id);
        return servicoRepository.save(servicoParaAtualizar);
    }

    public void removerServico(Integer id) {
        if (!servicoRepository.existsById(id)) {
            throw new ObjetoNaoEncontradoException("Serviço não encontrado");
        }

        servicoRepository.deleteById(id);
    }

}