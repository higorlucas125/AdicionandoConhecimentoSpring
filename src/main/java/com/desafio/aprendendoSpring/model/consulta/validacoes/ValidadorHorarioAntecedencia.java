package com.desafio.aprendendoSpring.model.consulta.validacoes;

import com.desafio.aprendendoSpring.model.consulta.dto.DadosAgendamentoConsulta;
import com.desafio.aprendendoSpring.service.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements Validador {

    public void validar(DadosAgendamentoConsulta consulta) {
        var dataConsulta = consulta.dateTime();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = java.time.Duration.between(agora, dataConsulta).toMinutes();

        if( diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
