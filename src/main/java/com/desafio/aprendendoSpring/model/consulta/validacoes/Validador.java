package com.desafio.aprendendoSpring.model.consulta.validacoes;

import com.desafio.aprendendoSpring.model.consulta.dto.DadosAgendamentoConsulta;

public interface Validador {

    void validar(DadosAgendamentoConsulta consulta);
}
