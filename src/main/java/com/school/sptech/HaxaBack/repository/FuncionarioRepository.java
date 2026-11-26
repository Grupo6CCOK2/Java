package com.school.sptech.HaxaBack.repository;

import com.school.sptech.HaxaBack.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByUsuarioId(Integer usuarioId);
    boolean existsByUsuarioId(Integer usuarioId);
}