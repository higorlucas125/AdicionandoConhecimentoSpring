package com.desafio.aprendendoSpring.repository;

import com.desafio.aprendendoSpring.model.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
