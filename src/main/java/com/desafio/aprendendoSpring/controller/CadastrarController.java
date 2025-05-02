package com.desafio.aprendendoSpring.controller;

import com.desafio.aprendendoSpring.model.usuario.Usuario;
import com.desafio.aprendendoSpring.model.usuario.dto.DadosAutenticaco;
import com.desafio.aprendendoSpring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastrar")
public class CadastrarController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody DadosAutenticaco cadastro) {
        if (usuarioRepository.existsByNome(cadastro.login())) {
            return ResponseEntity.badRequest().body("Usuário já cadastrado");
        }

        var encoder = new BCryptPasswordEncoder();
        var encodeSenha = encoder.encode(cadastro.senha());

        var usuario = new Usuario(cadastro.login(),encodeSenha);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuário cadastrado com sucesso");
    }
}
