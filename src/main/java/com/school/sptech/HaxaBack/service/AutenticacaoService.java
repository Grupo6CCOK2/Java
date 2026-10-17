package com.school.sptech.HaxaBack.service;

import com.school.sptech.HaxaBack.dto.ClienteDetalhesDto;
import com.school.sptech.HaxaBack.entity.Cliente;
import com.school.sptech.HaxaBack.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

  @Autowired
  private ClienteRepository usuarioRepository;

  // Método da interface implementada
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<Cliente> usuarioOpt = usuarioRepository.findByEmail(username);

    if (usuarioOpt.isEmpty()) {

      throw new UsernameNotFoundException(String.format("usuário: %s não encontrado", username));
    }

    return new ClienteDetalhesDto(usuarioOpt.get());
  }
}
