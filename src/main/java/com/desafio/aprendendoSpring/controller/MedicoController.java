package com.desafio.aprendendoSpring.controller;

import com.desafio.aprendendoSpring.model.medico.Medico;
import com.desafio.aprendendoSpring.model.medico.dto.DadosAtualizarMedico;
import com.desafio.aprendendoSpring.model.medico.dto.DadosCadastroMedico;
import com.desafio.aprendendoSpring.model.medico.dto.DadosDetalhamentoMedico;
import com.desafio.aprendendoSpring.model.medico.dto.DadosListagemMedico;
import com.desafio.aprendendoSpring.repository.MedicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {


    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico, UriComponentsBuilder uriComponentsBuilder) {
        var medico = new Medico(dadosCadastroMedico);
        medicoRepository.save(medico);

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listarMedicos(@PageableDefault(size = 10, sort={"nome"}) Pageable pageable) {
       var page = medicoRepository.findAllByStatusTrue(pageable).map(DadosListagemMedico::new);
       return ResponseEntity.ok(page);
    }



    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    //EXCLUSÃO FISICA
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir (@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);

        return ResponseEntity.noContent().build();
    }

    //EXCLUSÃO LÓGICA
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity excluirLogico(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.excluirLogico();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}

