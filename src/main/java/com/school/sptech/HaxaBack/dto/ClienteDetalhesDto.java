package com.school.sptech.HaxaBack.dto;

import com.school.sptech.HaxaBack.entity.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class ClienteDetalhesDto implements UserDetails {

    private final String nome;

    private final String email;

    private final String senha;

    private final Integer tipoUsuario;

    public ClienteDetalhesDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.senha = cliente.getSenha();
        this.tipoUsuario = cliente.getTipoUsuario();
    }

    public String getNome() {
        return nome;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String perfil;
        if (Integer.valueOf(2).equals(this.tipoUsuario)) {
            perfil = "ROLE_ADMIN";
        } else if (Integer.valueOf(1).equals(this.tipoUsuario)) {
            perfil = "ROLE_FUNCIONARIO";
        } else {
            perfil = "ROLE_CLIENTE";
        }
        return List.of(new SimpleGrantedAuthority(perfil));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
