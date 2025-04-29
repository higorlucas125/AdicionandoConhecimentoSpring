package com.desafio.aprendendoSpring.model.paciente.dto;

import com.desafio.aprendendoSpring.model.endereco.dto.DadosEndereco;

public record DadosCadastroPaciente(String nome,
                                    String email,
                                    String telefone,
                                    String cpf,
                                    DadosEndereco endereco) {
}
