package com.desafio.aprendendoSpring.model.consulta.dto;

import com.desafio.aprendendoSpring.model.enums.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(@NotNull
                                        Long idConsulta,

                                        @NotNull
                                        MotivoCancelamento motivo) {
}
