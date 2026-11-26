package com.school.sptech.HaxaBack.repository;

import com.school.sptech.HaxaBack.entity.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DependenteRepository extends JpaRepository<Dependente, Integer> {
    Optional<Dependente> findByIdDependentesAndUsuarioId(Integer idDependentes, Integer usuarioId);
}