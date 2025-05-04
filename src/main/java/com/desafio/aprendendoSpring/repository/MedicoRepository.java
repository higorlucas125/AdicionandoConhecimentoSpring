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
            select m.* from medicos m
            where
            m.status = 1
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico_id from consultas c
                where
                c.data = :data
                and
                c.motivo_cancelamento is null
            )
            order by rand()
            limit 1
        """, nativeQuery = true)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query(value = """
            select m.status
            from Medico m
            where
            m.id = :id
            """,nativeQuery = true)
    Boolean findAtivoById(Long id);
}
