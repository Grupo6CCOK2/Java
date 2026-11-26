package com.school.sptech.HaxaBack.repository;

import com.school.sptech.HaxaBack.entity.ItemServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemServicoRepository extends JpaRepository<ItemServico, Integer> {
    List<ItemServico> findByAgendamentoId(Integer agendamentoId);
    void deleteByAgendamentoId(Integer agendamentoId);
}