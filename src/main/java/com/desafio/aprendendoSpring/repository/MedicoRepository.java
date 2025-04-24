package com.desafio.aprendendoSpring.repository;

import com.desafio.aprendendoSpring.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
