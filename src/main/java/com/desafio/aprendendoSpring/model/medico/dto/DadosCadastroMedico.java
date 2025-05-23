package com.desafio.aprendendoSpring.model.medico.dto;

import com.desafio.aprendendoSpring.model.endereco.dto.DadosEndereco;
import com.desafio.aprendendoSpring.model.enums.Especialidade;
import com.desafio.aprendendoSpring.model.medico.Medico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record DadosCadastroMedico(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{5}-\\d{4}")
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco) {

    public DadosCadastroMedico (Medico medico){
        this(medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCrm(),
                medico.getEspecialidade(),
                new DadosEndereco(medico.getEndereco()));
    }
}
