package com.desafio.aprendendoSpring.model.paciente.dto;

import com.desafio.aprendendoSpring.model.endereco.dto.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
