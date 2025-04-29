package com.desafio.aprendendoSpring.model.medico.dto;

import com.desafio.aprendendoSpring.model.endereco.dto.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico(@NotNull  Long id, String nome, String telefone, DadosEndereco endereco) {
}
