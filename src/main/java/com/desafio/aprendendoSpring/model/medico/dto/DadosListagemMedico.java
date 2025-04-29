package com.desafio.aprendendoSpring.model.medico.dto;

import com.desafio.aprendendoSpring.model.enums.Especialidade;
import com.desafio.aprendendoSpring.model.medico.Medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(),medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade());
    }
}
