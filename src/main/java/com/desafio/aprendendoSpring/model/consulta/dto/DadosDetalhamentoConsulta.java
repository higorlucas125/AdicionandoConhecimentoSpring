package com.desafio.aprendendoSpring.model.consulta.dto;

import com.desafio.aprendendoSpring.model.consulta.Consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime dateTime) {
    public DadosDetalhamentoConsulta(Consulta consultas) {
        this(
                consultas.getId(),
                consultas.getMedico().getId(),
                consultas.getPaciente().getId(),
                consultas.getDateTime()
        );
    }
}
