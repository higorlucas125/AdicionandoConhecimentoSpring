package com.desafio.aprendendoSpring.model.consulta.validacoes;

import com.desafio.aprendendoSpring.model.consulta.dto.DadosAgendamentoConsulta;
import com.desafio.aprendendoSpring.service.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements Validador {

    public void validar(DadosAgendamentoConsulta consulta) {
        var dataConsulta = consulta.dateTime();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        var antesAbertaturaClinica = dataConsulta.getHour() < 7;
        var depoisFechamentoClinica = dataConsulta.getHour() > 18;

        if( domingo || antesAbertaturaClinica || depoisFechamentoClinica ){
            throw new ValidacaoException("Consulta fora do horário de funcionamentada da clínica");
        }
    }
}
