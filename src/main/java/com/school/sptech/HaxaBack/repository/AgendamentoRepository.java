package com.school.sptech.HaxaBack.repository;

import com.school.sptech.HaxaBack.entity.Agendamento;
import com.school.sptech.HaxaBack.entity.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    boolean existsByDataAndHorarioAndFuncionarioIdAndStatusNot(LocalDate data, LocalTime horario, Integer funcionarioId, StatusAgendamento status);

    boolean existsByDataAndHorarioAndFuncionarioIdAndStatusNotAndIdNot(LocalDate data, LocalTime horario, Integer funcionarioId, StatusAgendamento status, Integer id);
}
