package com.desafio.aprendendoSpring.controller;


import com.desafio.aprendendoSpring.model.consulta.dto.DadosAgendamentoConsulta;
import com.desafio.aprendendoSpring.model.consulta.dto.DadosCancelamentoConsulta;
import com.desafio.aprendendoSpring.model.consulta.dto.DadosDetalhamentoConsulta;
import com.desafio.aprendendoSpring.service.AgendaDeConsultas;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = agendaDeConsultas.agendar(dados);
        return ResponseEntity.ok().body(dto);
    }


    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agendaDeConsultas.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
