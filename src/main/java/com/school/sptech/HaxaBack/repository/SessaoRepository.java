package com.school.sptech.HaxaBack.repository;

import com.school.sptech.HaxaBack.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessaoRepository extends JpaRepository<Sessao, Integer> {

    Optional<Sessao> findByJtiAndAtivaTrue(String jti);

    Optional<Sessao> findByJti(String jti);

    Optional<Sessao> findByJtiAndUsuario_EmailAndAtivaTrue(String jti, String email);
}

