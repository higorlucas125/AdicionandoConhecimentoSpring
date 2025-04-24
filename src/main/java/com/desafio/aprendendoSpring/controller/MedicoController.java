package com.desafio.aprendendoSpring.controller;

import com.desafio.aprendendoSpring.model.Medico;
import com.desafio.aprendendoSpring.model.dto.DadosCadastroMedico;
import com.desafio.aprendendoSpring.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico medico) {
       medicoRepository.save(new Medico(medico));

    }
}
