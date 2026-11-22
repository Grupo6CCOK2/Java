package com.school.sptech.HaxaBack.service;

import com.school.sptech.HaxaBack.entity.Cliente;
import com.school.sptech.HaxaBack.entity.Sessao;
import com.school.sptech.HaxaBack.repository.SessaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;

    public SessaoService(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    @Transactional
    public Sessao criar(Cliente usuario, String jti) {
        Sessao sessao = new Sessao(usuario, jti);
        return sessaoRepository.save(sessao);
    }

    @Transactional(readOnly = true)
    public boolean sessaoEstaAtiva(String jti, String email) {
        if (jti == null || jti.isBlank() || email == null || email.isBlank()) {
            return false;
        }
        return sessaoRepository
                .findByJtiAndUsuario_EmailAndAtivaTrue(jti, email)
                .isPresent();
    }

    @Transactional
    public void desativar(String jti) {
        if (jti == null || jti.isBlank()) {
            return;
        }

        sessaoRepository.findByJti(jti).ifPresent(sessao -> {
            sessao.setAtiva(false);
            sessaoRepository.save(sessao);
        });
    }
}

