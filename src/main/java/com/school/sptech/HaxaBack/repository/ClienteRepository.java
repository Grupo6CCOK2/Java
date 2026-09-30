package com.school.sptech.HaxaBack.repository;

import com.school.sptech.HaxaBack.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByNomeAndSobrenome(String nome, String sobrenome);
    boolean existsByNomeAndSobrenomeAndIdNot(String nome, String sobrenome, Integer id);
}
