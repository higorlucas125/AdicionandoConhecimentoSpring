package com.desafio.aprendendoSpring.controller;

import com.desafio.aprendendoSpring.model.dto.DadosCadastroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void cadastrarMedico(@RequestBody DadosCadastroMedico medico) {
        // Lógica para cadastrar o médico
        System.out.println("Médico cadastrado: " + medico);

    }
}
