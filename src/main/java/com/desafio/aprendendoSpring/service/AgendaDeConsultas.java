package com.desafio.aprendendoSpring.service;

import com.desafio.aprendendoSpring.model.consulta.Consulta;
import com.desafio.aprendendoSpring.model.consulta.dto.DadosAgendamentoConsulta;
import com.desafio.aprendendoSpring.model.consulta.dto.DadosCancelamentoConsulta;
import com.desafio.aprendendoSpring.model.consulta.dto.DadosDetalhamentoConsulta;
import com.desafio.aprendendoSpring.model.consulta.validacoes.Validador;
import com.desafio.aprendendoSpring.model.medico.Medico;
import com.desafio.aprendendoSpring.repository.ConsultaRepository;
import com.desafio.aprendendoSpring.repository.MedicoRepository;
import com.desafio.aprendendoSpring.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<Validador> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta consulta) {
        //REGRA DE NEGOCIO

        //VALIDAR SE O PACIENTE E O MEDICO EXISTEM

        if(!pacienteRepository.existsById(consulta.idPaciente())){
            throw  new ValidacaoException("Id do paciente informado não existe!");
        }

        if ( consulta.idMedico() != null && !medicoRepository.existsById(consulta.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe!");
        }

        validadores.forEach(validador -> validador.validar(consulta));

        var medico = escolherMedico(consulta);

        if (medico == null){
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        var paciente = pacienteRepository.getReferenceById(consulta.idPaciente());




        var consultas = new Consulta(null, medico, paciente, consulta.dateTime(),null);

        consultaRepository.save(consultas);

        return new DadosDetalhamentoConsulta(consultas);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta consulta) {
        if (consulta.idMedico() != null){
            return medicoRepository.getReferenceById(consulta.idMedico());
        }

        if (consulta.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não é informado!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(consulta.especialidade(), consulta.dateTime());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
