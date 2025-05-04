package com.desafio.aprendendoSpring.repository;

import com.desafio.aprendendoSpring.model.enums.Especialidade;
import com.desafio.aprendendoSpring.model.medico.Medico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByStatusTrue(Pageable pageable);

    @Query(value = """ 
    SELECT * FROM medicos m
        WHERE m.especialidade = :especialidade
        AND m.status = 1
        AND m.id NOT IN (
            SELECT c.medico_id FROM consultas c
            WHERE c.data = :dateTime
        )
        ORDER BY RAND()
        LIMIT 1""", nativeQuery = true)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, @NotNull @Future LocalDateTime dateTime);
}
