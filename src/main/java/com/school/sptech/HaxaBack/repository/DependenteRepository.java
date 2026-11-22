package com.school.sptech.HaxaBack.repository;

import com.school.sptech.HaxaBack.entity.Dependente;
import com.school.sptech.HaxaBack.entity.DependenteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependenteRepository extends JpaRepository<Dependente, DependenteId> {
}
