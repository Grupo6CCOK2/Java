package com.school.sptech.HaxaBack.repository;

import com.school.sptech.HaxaBack.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByNomeAndSobrenome(String nome, String sobrenome);
    boolean existsByEmail(String email);
    Optional<Cliente> findByEmail(String email);
    boolean existsByNomeAndSobrenomeAndIdNot(String nome, String sobrenome, Integer id);
    boolean existsByEmailAndIdNot(String email, Integer id);
}
