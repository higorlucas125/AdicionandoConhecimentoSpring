package com.desafio.aprendendoSpring.model.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedico(@NotNull  Long id, String nome, String telefone, DadosEndereco endereco) {
}
