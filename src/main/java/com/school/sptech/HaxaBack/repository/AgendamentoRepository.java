package com.school.sptech.HaxaBack.repository;

import com.school.sptech.HaxaBack.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    boolean existsByDataAndHorario(LocalDate data, LocalTime horario);

    boolean existsByDataAndHorarioAndIdNot(LocalDate data, LocalTime horario, Integer id);
}
