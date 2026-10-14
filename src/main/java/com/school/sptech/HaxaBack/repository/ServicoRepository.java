package com.school.sptech.HaxaBack.repository;

import com.school.sptech.HaxaBack.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    boolean existsByTipoServico(String tipoServico);
    boolean existsByTipoServicoAndIdServicoNot(String tipoServico, Integer idServico);
}