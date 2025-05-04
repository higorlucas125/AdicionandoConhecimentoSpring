package com.desafio.aprendendoSpring.model.consulta.dto;

import com.desafio.aprendendoSpring.model.enums.Especialidade;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(Long idMedico, @NotNull @JsonAlias({"produto_id", "id_produto"})  Long idPaciente, @NotNull @Future  @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dateTime, Especialidade especialidade) {
}
