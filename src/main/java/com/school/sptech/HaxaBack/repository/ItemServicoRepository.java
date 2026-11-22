package com.school.sptech.HaxaBack.repository;

import com.school.sptech.HaxaBack.entity.ItemServico;
import com.school.sptech.HaxaBack.entity.ItemServicoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemServicoRepository extends JpaRepository<ItemServico, ItemServicoId> {
    List<ItemServico> findByAgendamentoId(Integer agendamentoId);
    void deleteByAgendamentoId(Integer agendamentoId);
}
