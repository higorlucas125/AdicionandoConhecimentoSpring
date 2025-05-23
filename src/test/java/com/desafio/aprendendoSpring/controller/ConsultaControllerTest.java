package com.desafio.aprendendoSpring.controller;

import com.desafio.aprendendoSpring.model.consulta.dto.DadosAgendamentoConsulta;
import com.desafio.aprendendoSpring.model.consulta.dto.DadosDetalhamentoConsulta;
import com.desafio.aprendendoSpring.model.enums.Especialidade;
import com.desafio.aprendendoSpring.service.AgendaDeConsultas;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJacksonTester;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJacksonTester;

    @MockBean
    private AgendaDeConsultas  agendaDeConsultas;

    @Test
    @DisplayName(value = "Deveria devolver codigo http 400 quando informacoes invalidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response =  mockMvc.perform(post("/consultas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    @DisplayName(value = "Deveria devolver codigo http 200 quando informacoes validas")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        when(agendaDeConsultas.agendar(any())).thenReturn(
                new DadosDetalhamentoConsulta(
                        null,2L,5L,data)
        );

        var response =  mockMvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosAgendamentoConsultaJacksonTester.write(
                        new DadosAgendamentoConsulta(2L,5L,data,especialidade)).getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonEsperado = dadosDetalhamentoConsultaJacksonTester.write(
                new DadosDetalhamentoConsulta(
                        null,2L,5l,data)
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    void cancelar() {
    }
}