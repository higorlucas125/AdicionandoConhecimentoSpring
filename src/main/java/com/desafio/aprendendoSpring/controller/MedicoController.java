package com.desafio.aprendendoSpring.controller;

import com.desafio.aprendendoSpring.model.Medico;
import com.desafio.aprendendoSpring.model.dto.DadosAtualizarMedico;
import com.desafio.aprendendoSpring.model.dto.DadosCadastroMedico;
import com.desafio.aprendendoSpring.model.dto.DadosListagemMedico;
import com.desafio.aprendendoSpring.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public Page<DadosListagemMedico> listarMedicos(@PageableDefault(size = 10, sort={"nome"}) Pageable pageable) {
       return medicoRepository.findAllByStatusTrue(pageable).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizarMedico(@RequestBody @Valid DadosAtualizarMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    //EXCLUSÃO FISICA
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir (@PathVariable Long id) {
        medicoRepository.deleteById(id);
    }

    //EXCLUSÃO LÓGICA
    @PatchMapping("/{id}")
    @Transactional
    public void excluirLogico(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.excluirLogico();
    }
}

