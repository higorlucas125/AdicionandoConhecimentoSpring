package com.desafio.aprendendoSpring.model.medico.dto;

import com.desafio.aprendendoSpring.model.endereco.Endereco;
import com.desafio.aprendendoSpring.model.enums.Especialidade;
import com.desafio.aprendendoSpring.model.medico.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
